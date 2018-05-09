package com.example.work.zsproject.view.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.work.zsproject.R;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * 
 *  日期选择器pop
 * @author wu
 * @date 2016-11-16
 */
public class DatePickPopupWindow extends PopupWindow implements OnClickListener,OnTabSelectListener,View.OnTouchListener{
	private OnDateSelectListener dateSelectListener;
	private View layout_top;
	private Context context;
	private DatePicker datePicker,datePicker2;
	private SegmentTabLayout segmentTabLayout;
	public DatePickPopupWindow(Context context) {
		super(context);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
		this.setOutsideTouchable(true);
		this.setAnimationStyle(R.style.popwindow_anim_style);
		this.context = context;
		LayoutInflater mLayoutInflater = LayoutInflater.from(context);
		View rootView = mLayoutInflater.inflate(R.layout.pop_date_picker, null);
		// -----确定和取消按钮
		Button btnSubmit = (Button) rootView.findViewById(R.id.btn_confirm);
		Button btnCancel = (Button) rootView.findViewById(R.id.btn_cancel);
		btnSubmit.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		layout_top = rootView.findViewById(R.id.layout_top);
		rootView.setOnTouchListener(this);

		segmentTabLayout = (SegmentTabLayout)rootView.findViewById(R.id.segmentTabLayout);
		segmentTabLayout.setTabData(new String[]{"开始时间","结束时间"});
		segmentTabLayout.setOnTabSelectListener(this);

		datePicker  = (DatePicker) rootView.findViewById(R.id.datePicker);
		datePicker2 = (DatePicker) rootView.findViewById(R.id.datePicker2);

		setDatePickerDividerColor(datePicker);
		setDatePickerDividerColor(datePicker2);
		// Init DatePicker
		int year;
		int month;
		int day;
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, month, day, null);
		datePicker2.init(year, month, day, null);
		setContentView(rootView);


	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()){
			case R.id.parentView:
				dismiss();
				break;
			case R.id.btn_confirm:
				if(dateSelectListener!=null) {
					String start = DateUtils.formatDate(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
					String end = DateUtils.formatDate(datePicker2.getYear(), datePicker2.getMonth() + 1, datePicker2.getDayOfMonth());
					if(DateUtils.date2mill3(start)>DateUtils.date2mill3(end)){
						Toast.makeText(context,"开始时间应晚于结束时间!",Toast.LENGTH_LONG).show();
						break;
					}
					dateSelectListener.onDateSelect(start,end);
				}
				dismiss();
				break;
			case R.id.btn_cancel:
				dismiss();
				break;
			default:
					break;
		}
	}

	@Override
	public void onTabSelect(int position) {
		if(position==0){
			datePicker.setVisibility(View.VISIBLE);
			datePicker2.setVisibility(View.GONE);
		}else{
			datePicker.setVisibility(View.GONE);
			datePicker2.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onTabReselect(int position) {

	}


	public void setTab(int index){
		if(index == -1){
			//隐藏选项卡
			segmentTabLayout.setVisibility(View.GONE);
			return;
		}
		segmentTabLayout.setCurrentTab(index);
		this.onTabSelect(index);
	}


	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		switch (motionEvent.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(motionEvent.getY()<layout_top.getY()){
					dismiss();
				}
				break;
			default:
					break;
		}

		return false;
	}

	public interface OnDateSelectListener {
		void onDateSelect(String start, String end);
	}

	public void setDateSelectListener(
			OnDateSelectListener onDateSelectListener) {
		this.dateSelectListener = onDateSelectListener;
	}

	/**
	 * 设置时间选择器的分割线颜色
	 *
	 * @param datePicker
	 */
	private void setDatePickerDividerColor(DatePicker datePicker) {
		// Divider changing:

		// 获取 mSpinners
		LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

		// 获取 NumberPicker
		LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
		for (int i = 0; i < mSpinners.getChildCount(); i++) {
			NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

			Field[] pickerFields = NumberPicker.class.getDeclaredFields();
			for (Field pf : pickerFields) {
				if (pf.getName().equals("mSelectionDivider")) {
					pf.setAccessible(true);
					try {
						pf.set(picker, new ColorDrawable(Color.parseColor("#e1e1e1")));//设置分割线颜色
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (Resources.NotFoundException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
}
