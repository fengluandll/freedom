package mx.gob.tsjdf.cfdi.recibos.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class TxtFormatter
  extends Formatter
{
  public String format(LogRecord rec)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
    
    StringBuffer buf = new StringBuffer(1000);
    if (rec.getLevel().intValue() == Level.WARNING.intValue())
    {
      buf.append("ADVERTENCIA");
      buf.append("\t");
    }
    else if (rec.getLevel().intValue() == Level.SEVERE.intValue())
    {
      buf.append("ERROR GRAVE");
      buf.append("\t");
    }
    else
    {
      buf.append(rec.getLevel());
    }
    buf.append(" ");
    buf.append(sdf.format(Long.valueOf(rec.getMillis())));
    buf.append("\t");
    buf.append(formatMessage(rec).replaceAll("\n", " "));
    buf.append('\n');
    
    return buf.toString();
  }
  
  public String getHead(Handler h)
  {
    return "Generacion de archivos TXT  " + new Date() + "\n";
  }
  
  public String getTail(Handler h)
  {
    return "\n";
  }
}
