package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto", group = "Auto")
public class auto extends LinearOpMode
{

    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB;
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

        rightDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveF.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        //Forward, full speed, 1 m
        forward(1, 1000);

        /* forward(untill in front of kryptoboc)
        turn (90)
        forward(a little more)
        run lift(1)
        */


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

