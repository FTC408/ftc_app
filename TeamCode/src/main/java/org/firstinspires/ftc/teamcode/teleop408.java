package org.firstinspires.ftc.teamcode;

/**
 * Created by Austin on 10/23/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Austin on 10/19/2017.
 */

@TeleOp(name="TeleOperations", group="Linear Opmode")
@Disabled
public class teleop408 extends LinearOpMode
{


    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator;
    Float left, right;


    @Override
    public void runOpMode() throws InterruptedException
    {

        //Initilization Procedures
        //Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");

       //leftDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftDriveF.setDirection(DcMotorSimple.Direction.REVERSE);
//gamepad1.dpadup
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");

        elevator = hardwareMap.dcMotor.get("elevator");


        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        while (opModeIsActive()) {
            //Assign values to the doubles using the gamepad values
            left = -gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            //Assign power values to the motors, using the values we just made with the gamepad lines
            leftDriveF.setPower(left);
            leftDriveB.setPower(left);
            rightDriveF.setPower(right);
            rightDriveB.setPower(right);

            if (gamepad1.dpad_up == true) {
                elevator.setPower(1);
            } else if (gamepad1.dpad_down == true) {
                elevator.setPower(1);
            } else {
                elevator.setPower(0);
            }

        }


    }
}
