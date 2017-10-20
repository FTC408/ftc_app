package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Austin on 10/19/2017.
 */

public class TeleOp extends LinearOpMode
{

    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB;
    Float left, right;


    @Override
    public void runOpMode() throws InterruptedException
    {

        //Initilization Procedures
        //Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");


        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        //Assign values to the doubles using the gamepad values
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        //Assign power values to the motors, using the values we just made with the gamepad lines
        leftDriveF.setPower(left);
        leftDriveB.setPower(left);
        rightDriveF.setPower(right);
        rightDriveB.setPower(right);
    }
}
