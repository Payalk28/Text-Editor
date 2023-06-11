import java.awt.*;
import java.awt.FileDialog;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.awt.GraphicsEnvironment.*;
       
public class textedit extends Frame implements ActionListener
{
  Frame f;
  FileDialog save,open;
  MenuBar m;
  Menu m1,m2,m3;
  MenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8;
  TextArea t;
  Choice c,c1,c2;
  Dialog d; Button b;
  MenuShortcut s;
   String data="";


  public static void main(String arg[])
  {
    new textedit();
  }

  textedit()
  {
    f=new Frame("untitled");
    f.setSize(500,500);
    f.setVisible(true);

    //create menubar
    m=new MenuBar();
    f.setMenuBar(m);
    //add menu to menubar
    m1=new Menu("File");
    m2=new Menu("Edit");
    m3=new Menu("Format");
    m.add(m1); m.add(m2); m.add(m3);
    
    //add file menuitem
    s=new MenuShortcut(KeyEvent.VK_N);
    mi1=new MenuItem("new");  mi1.setShortcut(s);
    s=new MenuShortcut(KeyEvent.VK_S);
    mi2=new MenuItem("save"); mi2.setShortcut(s);
    s=new MenuShortcut(KeyEvent.VK_O);
    mi3=new MenuItem("open"); mi3.setShortcut(s);
    s=new MenuShortcut(KeyEvent.VK_Q);
    mi7=new MenuItem("exit"); mi7.setShortcut(s);
    m1.add(mi1); m1.add(mi2); m1.add(mi3); m1.add(mi7);

    //add format menuitem
    mi4=new MenuItem("Font");
    m3.add(mi4);

    //add edit menuitem
    s=new MenuShortcut(KeyEvent.VK_C);
    mi5=new MenuItem("copy"); mi5.setShortcut(s);
    s=new MenuShortcut(KeyEvent.VK_V);
    mi6=new MenuItem("paste"); mi6.setShortcut(s);
    s=new MenuShortcut(KeyEvent.VK_X);
    mi8=new MenuItem("cut");  mi8.setShortcut(s);
    m2.add(mi8); m2.add(mi5); m2.add(mi6);


    t=new TextArea();
    f.add(t);

    //add actionlistener
    m1.addActionListener(this);
    m2.addActionListener(this);
    m3.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent ae)
  {
    String str=ae.getActionCommand();
   
    if(str.equals("new"))
    {
       t.setText("");   
       f.setTitle("untitled");
    }     
    else if(str.equals("save"))
    {
       save=new FileDialog(f,"save",FileDialog.SAVE);
       save.setVisible(true); 
   
       try
       {
	       String s = t.getText();
	       File f = new File(save.getFile());
	       FileWriter fw = new FileWriter(f);
	       fw.write(s);
          fw.close();
          
       }
       catch(Exception e)
       {

       }   
      f.setTitle(save.getFile()); 
    }

    else if(str.equals("open"))
    {
       open=new FileDialog(f,"open",FileDialog.LOAD);
       open.setVisible(true);

       try
       {
	   
          FileInputStream f= new FileInputStream(open.getFile());
          int size=f.available();
          byte buf[]=new byte[size];
          f.read(buf);
          String str1=new String(buf);
          t.setText(str1);
      
       }
       catch(Exception e)
       {

       } 
       f.setTitle(open.getFile());  
    }

     else if(str.equals("exit"))
    {
        f.setVisible(false);      
    }

    else if(str.equals("Font"))
    {
       d=new Dialog(f,"Font",true);
       d.setSize(400,400);
       d.setLayout(new FlowLayout());
       //GraphicsEnvironment ge=getLocaleGraphicsEnvironment();
      
       String f[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();       
       d.add(new Label("Font:"));
       //choice to show all fonts
       c=new Choice();
       int i=0;
       while(i!=234)
       {
         c.add(f[i]);
         i++;
       }
       d.add(c);
       //choice to show size of font
       d.add(new Label("Size:"));
       c1=new Choice();
       String count;
       i=2;
       while(i!=200)
       {
      
         c1.add(String.valueOf(i));
         i+=2;
       }      
       d.add(c1);       

       b=new Button("ok");
       b.addActionListener(this);
       d.add(b);
       d.setVisible(true);
       
       int s=Integer.parseInt(c1.getSelectedItem());
       Font myFont =new Font(c.getSelectedItem(),Font.BOLD,s);
       t.setFont(myFont); 
             
    }
    else if(str.equals("copy"))
    {
        data=t.getSelectedText();           
    }
    else if(str.equals("paste"))
    {   
        int pos=t.getCaretPosition();     
        t.insertText(data,pos);    
      
    }
    else if(str.equals("cut"))
    {   
          data=t.getSelectedText();
         String cut=t.getText().replace(t.getSelectedText(),"");   
         t.setText(cut);
    }
    else if(str.equals("ok"))
    {
        d.setVisible(false);   
    }
    
    
     
  }
}