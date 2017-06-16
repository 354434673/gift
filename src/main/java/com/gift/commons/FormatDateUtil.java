package com.gift.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * jstl时间格式化,将long转为String
 * @ClassName:  FormatDateUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author YangNingSheng
 * @date 2017年6月15日 上午9:40:38
 */
public class FormatDateUtil extends TagSupport{

    private static final Long serialVersionUID = 6464168398214506236L;
    
    
    private String value;
    
    @Override
    public int doStartTag() throws JspException {
        String vv = "" + value;
        try {
            Long time = Long.valueOf(vv.trim());
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            SimpleDateFormat dateformat = new SimpleDateFormat(DateUtil.yyyyMMdd);
            String s = dateformat.format(c.getTime());
            pageContext.getOut().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }
}
