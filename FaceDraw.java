//@author: Mohammad Alabed

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;


class OvalDraw extends Oval
{
    public OvalDraw()
    {
        super(0,0,0,0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn)
    {
        super(positionXIn, positionYIn, widthIn, heightIn);
    }

    public String toString()
    {
        return String.format("x=%d, y=%d, w=%d, h=%d",getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public void paintComponent(Graphics g)
    {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
        System.out.println(toString());
    }
}

class FaceDrawFace extends OvalDraw
{
    private OvalDraw leftEye;
    private OvalDraw rightEye;
    //0=smiling 1=frown 2=straight
    private int emotion = Utils.GetNumberBetweenInclusive(0,2);
    
    public FaceDrawFace()
    {
        super(0,0,0,0);
        leftEye = new OvalDraw(0,0,0,0);
        rightEye = new OvalDraw(0,0,0,0);
    }

    public FaceDrawFace(int positionXIn, int positionYIn, int widthIn, int heightIn)
    {
        super(positionXIn, positionYIn, widthIn, heightIn);
        
        int leftEyeHeight = heightIn / 4;
        int leftEyeWidth = leftEyeHeight / 2;
        int leftEyePositionX = positionXIn + (widthIn / 3) - (leftEyeWidth / 3);
        int leftEyePositionY = positionYIn + (heightIn / 3) - (leftEyeHeight / 2);
        leftEye = new OvalDraw(leftEyePositionX,leftEyePositionY,leftEyeWidth,leftEyeHeight);

        int rightEyeHeight = heightIn / 4;
        int rightEyeWidth = rightEyeHeight / 2;
        int rightEyePositionX = positionXIn + (widthIn / 2) + 10;
        int rightEyePositionY = positionYIn + (heightIn / 3) - (rightEyeHeight / 2);
        rightEye = new OvalDraw(rightEyePositionX,rightEyePositionY,rightEyeWidth,rightEyeHeight);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (emotion == 0)
        {
            g.setColor(Color.blue);
            g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
            leftEye.paintComponent(g);
            rightEye.paintComponent(g);
            g.drawArc(getPositionX(), getPositionY()-(getHeight()/5), getWidth(), getHeight()-10, -45, -90);
            g.setColor(Color.black);
        }
        else if (emotion == 1)
        {
            g.setColor(Color.red);
            g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
            leftEye.paintComponent(g);
            rightEye.paintComponent(g);
            g.drawArc(getPositionX(), getPositionY()+(getHeight() / 2), getWidth(), getHeight()-10, 45, 90);
            g.setColor(Color.black);
        }
        else
        {
            g.setColor(Color.green);
            g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
            leftEye.paintComponent(g);
            rightEye.paintComponent(g);
            g.drawLine(getPositionX()+(getWidth()/4), getPositionY()+(getHeight()/2)+20, getPositionX()+getWidth()-30, getPositionY()+(getHeight()/2)+20);
            g.setColor(Color.black);
        }
    }
}

class FaceDrawPanel extends JPanel
{
    private FaceDrawFace myFaceDrawFace;
    private int random = Utils.GetNumberBetweenInclusive(3,10);
    private ArrayList<FaceDrawFace> faceList = new ArrayList<FaceDrawFace>();

    public FaceDrawPanel()
    {
        for (int i = 0; i < random; i++)
        {
            faceList.add(myFaceDrawFace = new FaceDrawFace(Utils.GetNumberBetweenInclusive(-50,600),Utils.GetNumberBetweenInclusive(-50,400),
            Utils.GetNumberBetweenInclusive(100,200),Utils.GetNumberBetweenInclusive(100,200)));
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (FaceDrawFace faces: faceList)
        {
            faces.paintComponent(g);
        }
        
        System.out.println("There are " + random + " faces!");
    }
}

class FaceFrame extends JFrame
{
    public FaceFrame()
    {
        super("FaceDraw!");
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FaceDrawPanel myFaceDrawPanel = new FaceDrawPanel();
        add(myFaceDrawPanel);

        setVisible(true);
    }
}

public class FaceDraw
{
    public static void main(String[] args)
    {
        FaceFrame myFaceFrame = new FaceFrame();
    }
}
