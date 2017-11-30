package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Austin on 11/14/2017.
 */

public class robot extends LinearOpMode
{
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator, elevator2;
    ColorSensor colorLeft, colorRight;
    DistanceSensor sensorDistanceLeft, sensorDistanceRight;
    CRServo intakeRight, intakeLeft;
    Servo armRight, armLeft;

    public void init(int zed){
        //Initilization Procedures
        // Configuration in the phone, this allows the motors to control physical objects that the phone is connected to

        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");
        elevator =  hardwareMap.dcMotor.get("elevator");
        elevator2 = hardwareMap.dcMotor.get("elevator2");

        intakeRight = hardwareMap.crservo.get("intakeRight");
        intakeLeft = hardwareMap.crservo.get("intakeLeft");

        armRight = hardwareMap.servo.get("armRight");
        armLeft = hardwareMap.servo.get("armLeft");

        rightDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveF.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // get a reference to the color sensor
        colorLeft = hardwareMap.get(ColorSensor.class, "colorLeft");
        colorRight = hardwareMap.get(ColorSensor.class, "colorRight");

        // get a reference to the distance sensor that shares the same name.
        sensorDistanceLeft = hardwareMap.get(DistanceSensor.class, "colorLeft");
        sensorDistanceRight = hardwareMap.get(DistanceSensor.class, "colorRight");

        colorLeft.enableLed(false);
        colorRight.enableLed(false);

        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armRight.setPosition(0.28);
        armLeft.setPosition(0.8);

    }

    public void placeBlock()
    {
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1500);
        intakeLeft.setPower(0);
        intakeRight.setPower(0);

    }

    //If red == true, on red side, else on blue side
      public void jewel(boolean red)
    {
        if (red == true) //Wanted Red
        {
            armRight.setPosition(0.75);
            sleep(1000);
            //If Red
            if (ColorTest(colorRight) == 1)
            {
                forward(0.3, 50);
                forward(0); sleep(500);
                armRight.setPosition(0.28);
                sleep(1000);
                forward(-0.3, 50);
                forward(0); sleep(500);
                //Away from color sensor
            }
            //If blue
            else if (ColorTest(colorRight) == 0)
            {
                forward(-0.3, 50);
                forward(0); sleep(500);
                armRight.setPosition(0.28);
                sleep(1000);
                forward(0.3, 50);
                forward(0); sleep(500);
                //Towards color sensor
            }
            //If none
            else if (ColorTest(colorRight) == 0.5)
            {
                //Skip selective action
            }
            armRight.setPosition(0.28);
            sleep(1000);
        }
        else //Wanted Blue
        {
            armLeft.setPosition(0.4);
            sleep(1000);
            //If Red
            if (ColorTest(colorLeft) == 1)
            {
                forward(-0.3, 60);
                forward(0); sleep (500);
                armLeft.setPosition(0.8);
                sleep(1000);
                forward(0.3, 60);
                forward(0); sleep(500);
                //Away from color sensor
            }
            //If blue
            else if (ColorTest(colorLeft) == 0)
            {
                forward(0.3, 60);
                forward(0); sleep(500);
                armLeft.setPosition(0.8);
                sleep(1000);
                forward(-0.3, 60);
                forward(0); sleep(500);
                //Towards color sensor
            }
            //If none
            else if (ColorTest(colorLeft) == 0.5)
            {
                //Skip selective action
            }
            armLeft.setPosition(0.8);
            sleep(1000);
        }
    }
    //Returns 1 if red, 0 if blue, 0.5 if neither
    public double ColorTest(ColorSensor color)
    {
        if (color.red() > color.blue())
        {
            return 1;
        }
        if (color.blue() > color.red())
        {
            return 0;
        }
        return 0.5;
    }

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

    //Positive Degrees to the right, negative to the left
    public void turn(double power, int degrees)
    {
        double diameter = 245 * 2; //245 mm radius
        int pos = leftDriveF.getCurrentPosition();
        double Circumfrence = Math.PI * diameter;
        double distance = (Math.abs(degrees) * Circumfrence) /180;

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

    public void runOpMode() throws InterruptedException{}

    //Updates the telemetry data of the robot so we can see what is going on with the sensors while driving
    public void telemetry ()
    {
        //get position
        telemetry.addData("Motor Positions", "");
        telemetry.addData("Right Back Motor Position: ", rightDriveB.getCurrentPosition());
        telemetry.addData("Right Front Motor Position: ", rightDriveF.getCurrentPosition());
        telemetry.addData("Left Back Motor Position: ", leftDriveB.getCurrentPosition());
        telemetry.addData("Left Front Motor Position: ", leftDriveF.getCurrentPosition());
        telemetry.addData("Elevator Position: ", elevator.getCurrentPosition());
        telemetry.addData("", "");

        //get power
        telemetry.addData("Motor Powers", "");
        telemetry.addData("Right Back Motor Power: ", rightDriveB.getPower());
        telemetry.addData("Right Front Motor Power: ", rightDriveF.getPower());
        telemetry.addData("Left Back Motor Power: ", leftDriveB.getPower());
        telemetry.addData("Left Front Motor Power: ", leftDriveF.getPower());
        telemetry.addData("", "");

        if (colorLeft.red() > colorLeft.blue())
        {
            telemetry.addData("Color Left: ", "Red");
        }
        if (colorLeft.red() < colorLeft.blue())
        {
            telemetry.addData("Color Left: ", "Blue");
        }
        if (colorLeft.red() == colorLeft.blue())
        {
            telemetry.addData("Color Left: ", "Neither");
        }

        if (colorRight.red() > colorRight.blue())
        {
            telemetry.addData("Color Right: ", "Red");
        }
        if (colorRight.red() < colorRight.blue())
        {
            telemetry.addData("Color Right: ", "Blue");
        }
        if (colorRight.red() == colorRight.blue())
        {
            telemetry.addData("Color Right: ", "Neither");
        }

        telemetry.update();
    }
}
