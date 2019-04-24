package mx.gob.tsjdf.cfdi.recibos.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class HtmlFormatter
  extends Formatter
{
  public String format(LogRecord rec)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
    
    StringBuffer buf = new StringBuffer(1000);
    buf.append("<tr>");
    if (rec.getLevel().intValue() == Level.WARNING.intValue())
    {
      buf.append("<td style='border-style:dotted; color:orange;'>");
      buf.append("<b>");
      
      buf.append("ADVERTENCIA");
      buf.append("</b>");
      buf.append("</td>");
    }
    else if (rec.getLevel().intValue() == Level.SEVERE.intValue())
    {
      buf.append("<td style='border-style:dotted; color:red;'>");
      buf.append("<b>");
      
      buf.append("ERROR GRAVE");
      buf.append("</b>");
      buf.append("</td>");
    }
    else
    {
      buf.append("<td style='border-style:dotted; color:green'>");
      buf.append("<b>");
      buf.append(rec.getLevel());
      buf.append("</b>");
      buf.append("</td>");
    }
    buf.append("<td style='border-style:dotted;width:200'>");
    buf.append(sdf.format(Long.valueOf(rec.getMillis())));
    buf.append("</td>");
    







    buf.append("<td style='border-style:dotted;'>");
    buf.append(formatMessage(rec).replaceAll("\n", "<br />"));
    buf.append("</td>");
    buf.append("</tr>");
    buf.append('\n');
    
    return buf.toString();
  }
  
  public String getHead(Handler h)
  {
    return "<HTML><HEAD><title>Generacion de series y folios  " + new Date() + "</title></HEAD><BODY><table border='1' width='1300' style='border-style:dotted;color:#333333;font-family: Tahoma, Geneva, sans-serif;'>\n";
  }
  
  public String getTail(Handler h)
  {
    return "</table></BODY></HTML>\n";
  }
}
