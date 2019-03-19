package com.example.abaka.homework;

public class Bill {
    public int id = -1;
    public String _date;//日期
    public float _money;//钱
    public String _comment;//备注
    public String _name;//用户
    public String _type;//类型

    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }

    public static int daysOfYear(int year, int month, int day) {
        int mon[] = {
          31, 28, 31, 30, 31, 30, 31, 31, 30,31, 30, 31
        };
        int days = 0;
        for (int i = 0; i < month -1; ++i)
            days += mon[i];
        if (month > 2 && isLeapYear(year))
            days += 1;
        days += day;
        return days;
    }
    @Override
    public String toString() {
        return
                "id=" + id  +
                "  " + _date +
                "  " + _money +
                "  " + _comment +
                "  " + _type  ;
    }

    public static int getWeekth(String _date) {
        String[] elems = new String[3];
        elems[0] = _date.substring(0, 4);//截取年
        elems[1] = _date.substring(5, 7);
        elems[2] = _date.substring(8, 10);
        //String[] elems = _date.split("-");
        int year = Integer.parseInt(elems[0]);
        int month = Integer.parseInt(elems[1].charAt(0) == '0' ? elems[1].substring(1) : elems[1]);
        int day = Integer.parseInt(elems[2].charAt(0) == '0' ? elems[2].substring(1) : elems[2]);

        int days = daysOfYear(year, month, day);
        return days % 7 == 0 ? days / 7 : days / 7 + 1;
    }
}
