package udf;

import com.soteradefense.hive.udf.SimpleDateFormatUDF;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests our SimpleDateFormatUDF
 */
public class SimpleDateFormatUDFTest {

    SimpleDateFormatUDF sdfUdf;

    @Before
    public void setup() {
        sdfUdf = new SimpleDateFormatUDF();
    }

    @Test
    public void testExpectedData() {
        Text sampleDate1 = new Text("Tue Jan 23 14:12:33 2013");
        Text sampleDate2 = new Text("2013-12-13 24:00:00");
        Text sampleDate3 = new Text("12:13am on 12/25/2007");

        Text inputFormat1 = new Text("EEE MMM dd kk:mm:ss yyyy");
        Text inputFormat2 = new Text("yyyy-MM-dd HH:mm:ss");
        Text inputFormat3 = new Text("hh:mma' on 'MM/dd/yyyy");

        Text outputFormat = new Text("yyyy-MM-dd HH:mm:ss");

        Text out1 = sdfUdf.evaluate(sampleDate1, inputFormat1, outputFormat);
        Text out2 = sdfUdf.evaluate(sampleDate2, inputFormat2, outputFormat);
        Text out3 = sdfUdf.evaluate(sampleDate3, inputFormat3, outputFormat);

        assertEquals("2013-01-23 14:12:33", out1.toString());
        assertEquals("2013-12-14 00:00:00", out2.toString());
        assertEquals("2007-12-25 00:13:00", out3.toString());

    }

    @Test
    public void testEmptyData() {
        Text sampleDate2 = new Text("2013-12-13 24:00:00");
        Text sampleDate3 = new Text("12:13am on 12/25/2007");

        Text inputFormat1 = new Text("EEE MMM dd kk:mm:ss yyyy");
        Text inputFormat3 = new Text("hh:mma' on 'MM/dd/yyyy");

        Text outputFormat = new Text("yyyy-MM-dd HH:mm:ss");

        Text out1 = sdfUdf.evaluate(new Text(""), inputFormat1, outputFormat);
        Text out2 = sdfUdf.evaluate(sampleDate2, new Text(""), outputFormat);
        Text out3 = sdfUdf.evaluate(sampleDate3, inputFormat3, new Text(""));

        assertEquals(null, out1);
        assertEquals(null, out2);
        assertEquals(null, out3);
    }

    @Test
    public void testNullData() {
        Text sampleDate2 = new Text("2013-12-13 24:00:00");
        Text sampleDate3 = new Text("12:13am on 12/25/2007");

        Text inputFormat1 = new Text("EEE MMM dd kk:mm:ss yyyy");
        Text inputFormat3 = new Text("hh:mma' on 'MM/dd/yyyy");

        Text outputFormat = new Text("yyyy-MM-dd HH:mm:ss");

        Text out1 = sdfUdf.evaluate(null, inputFormat1, outputFormat);
        Text out2 = sdfUdf.evaluate(sampleDate2, null, outputFormat);
        Text out3 = sdfUdf.evaluate(sampleDate3, inputFormat3, null);

        assertEquals(null, out1);
        assertEquals(null, out2);
        assertEquals(null, out3);
    }

}
