package com.soteradefense.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormatUDF provides the Java SimpleDateFormat capabilities in Hive
 */
@Description(
        name = "SimpleDateFormat",
        value = "_FUNC_(str, str, str) -  Attempts to convert the value from the inputFormat to the outputFormat",
        extended = "SimpleDateFormat UDF is essentialy java.text.SimpleDateFormat and subsequently follows the same rules " +
                "for specifying the input and output formats " +
                "(see: http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html for more information)"
)
public class SimpleDateFormatUDF extends UDF {

    public Text evaluate(final Text s, final Text inputFormat, final Text outputFormat) {

        if (s == null || "".equals(s.toString()) ||
            inputFormat == null || "".equals(inputFormat.toString()) ||
            outputFormat == null || "".equals(outputFormat.toString())) {
            System.err.println("Empty data being passed in");
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat.toString());
        Date inputDate = null;
        try {
            inputDate = dateFormat.parse(s.toString());
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
            return null;
        }

        SimpleDateFormat givenFormat = new SimpleDateFormat(outputFormat.toString());
        String outputString = givenFormat.format(inputDate);

        return new Text(outputString);

    }

}
