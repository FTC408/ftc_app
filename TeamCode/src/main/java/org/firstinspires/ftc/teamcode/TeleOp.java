package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Austin on 10/19/2017.
 */

public class TeleOp extends LinearOpMode
{

    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB;
    Float left, right;
    

    @Override
    public void runOpMode() throws InterruptedException
    {

        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");

        waitForStart();

        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        leftDriveF.setPower(left);
        leftDriveB.setPower(left);
        rightDriveF.setPower(right);
        rightDriveB.setPower(right);
    }
}
