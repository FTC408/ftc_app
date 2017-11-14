package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue", group = "Auto")
public class autoBlueTurn extends LinearOpMode
{

    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB;
    ColorSensor color;
    Float left, right;
    double ticksPerRev = 288;
    double gearRatio = 1.33;
    double diameter = 101.6;

    public void runOpMode() throws InterruptedException
    {
        //Initilization Procedures
        //Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");

        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");

        //color = hardwareMap.colorSensor.get("color");

        //color.enableLed(false);

        rightDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveF.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        forward(0.5, 914);
        forward(0);
        sleep(500);

        turn(0.5,-90);
        forward(0);
        sleep(500);

        forward(0.5,457);
        forward(0);
        sleep(500);




    }

  /*  public void jewel()
    {

        //strafe left
        strafe(-0.5, 50);

        //lower arm

        //If Red
        if (ColorTest() == 1)
        {
            forward(-0.5, 100);
            forward(0.5, 100);
            //Away from color sensor
        }
        //If blue
        else if (ColorTest() == 0)
        {
            forward(0.5, 100);
            forward(-0.5, 100);
            //Towards color sensor
        }
        //If none
        else if (ColorTest() == 0.5)
        {
            //Skip selective action
        }

        //strafe right
        strafe(0.5, 50);

    }

    //Returns 1 if red, 0 if blue, 0.5 if neither
    public double ColorTest()
    {
        if (color.red() > color.blue())
        {
            return 1;
        }
        else if (color.blue() > color.red())
        {
            return 0;
        }
        else
        {
            return 0.5;
        }

    }*/

    public void leftPower(double power)
    {
        leftDriveB.setPower(power);
        leftDriveF.setPower(power);
    }

    public void rightPower(double power)
    {
        rightDriveF.setPower(power);
        rightDriveB.setPower(power);
    }

    public void forward(double power)
    {
        leftPower(power);
        rightPower(power);
    }

    public void forward(double power, int mm)
{
    int pos = leftDriveF.getCurrentPosition();
    //Forward 1 meter to test encoders once we get the chance to use them

    if (power >= 0) {
        while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
            forward(power);
        }
    }
    else
    {
        while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
            forward(power);
        }
    }
}
    //Positive power is to the right, negative to the left
    public void strafe(double power, int mm)
    {
        int pos = leftDriveF.getCurrentPosition();
        //Forward 1 meter to test encoders once we get the chance to use them

        //Strafe Right
        if (power >= 0) {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                strafeRight(Math.abs(power));
            }
        }
        //Strafe left
        else {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                strafeLeft(Math.abs(power));
            }
        }
    }

    //Positive Degrees to the right, negative to the left
    public void turn(double power, int degrees)
    {
     double diameter = 245 * 2; //245 mm radius
     int pos = leftDriveF.getCurrentPosition();
     double Circumfrence = Math.PI * diameter;
     double distance = (degrees * Circumfrence) /180;

        if (degrees >= 0) {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(distance))) {
                right(Math.abs(power));
            }
        }
        else
        {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(distance))) {
                left(Math.abs(power));
            }
        }
    }

    public void strafeRight(double power)
    {
        leftDriveF.setPower(power);
        leftDriveB.setPower(-power);
        rightDriveF.setPower(-power);
        rightDriveB.setPower(power);
    }

    public void strafeLeft(double power)
    {
        leftDriveF.setPower(-power);
        leftDriveB.setPower(power);
        rightDriveF.setPower(power);
        rightDriveB.setPower(-power);
    }

    public void right(double power)
    {
        leftPower(power);
        rightPower(-power);
    }
    public void left(double power)
    {
        leftPower(-power);
        rightPower(power);
    }

    public double tickstomm(int ticks){

    double mm = (ticks * 2.955);
    return mm;
    }

   //Go forward for such and such distance
    public double mmtoticks(double mm)
    {
        double ticks = (mm / 2.955);
        return ticks;
    }



}

