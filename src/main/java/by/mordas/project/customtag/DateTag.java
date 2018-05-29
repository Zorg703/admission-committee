package by.mordas.project.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 Author: Sergei Mordas
 Date: 21.05.2018
 ***/
public class DateTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDate.now().format(formatter);

        JspWriter out = pageContext.getOut();

        try {
            out.write(date);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
