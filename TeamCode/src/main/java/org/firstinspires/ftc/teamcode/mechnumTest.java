package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="mechnum", group="Linear Opmode")
public class mechnumTest extends LinearOpMode {

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

        //leftDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftDriveF.setDirection(DcMotorSimple.Direction.REVERSE);

        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");


        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        while (opModeIsActive())
        {

            double magnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));

            double x = gamepad1.left_stick_x;
            double x2 = gamepad1.right_stick_x;
            boolean leftRight, rightLeft;

            if (x < 0) //manipulates the x value so that the inverse tangent will stay within its boundaries
            {
                x = -x;
                leftRight = false; //keeps a record of whether x was positive or negative
            }
            else
            {
                leftRight = true;
            }

            if (x2 < 0) //manipulates the x value so that the inverse tangent will stay within its boundaries
            {
                x2 = -x2;
                rightLeft = false; //keeps a record of wheter x was positive or negative
            }
            else
            {
                rightLeft = true;
            }

            //L-forward
            if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) < (Math.PI)/2)) && leftRight == true) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(magnitude);
            }

             else if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) < (Math.PI)/2)) && leftRight == false) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(magnitude);
            }

            //L-right
             else if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) > (-Math.PI)/4)) && leftRight == true) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(magnitude);
            }

            //L-left
             else if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) > (-Math.PI)/4)) && leftRight == false) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(-magnitude);
            }

            //L-back
             else if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) >= (-Math.PI)/2)) && leftRight == true) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(-magnitude);
            }

             else if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/x) && Math.atan(gamepad1.left_stick_y/x) >= (-Math.PI)/2)) && leftRight == false) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(-magnitude);
            }

            else {
                 leftDriveF.setPower(0);
                 leftDriveB.setPower(0);
             }

            //R-forward
            if (((Math.PI/4 < Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) < (Math.PI)/2)) && rightLeft == true) {
                rightDriveF.setPower(magnitude);
                rightDriveB.setPower(magnitude);
            }

            else if (((Math.PI/4 < Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) < (Math.PI)/2)) && rightLeft == false) {
                rightDriveF.setPower(magnitude);
                rightDriveB.setPower(magnitude);
            }

            //R-right
            else if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) > (-Math.PI)/4)) && rightLeft == true) {
                rightDriveF.setPower(-magnitude);
                rightDriveB.setPower(magnitude);
            }

            //R-left
            else if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) > (-Math.PI)/4)) && rightLeft == false) {
                rightDriveF.setPower(magnitude);
                rightDriveB.setPower(-magnitude);
            }

            //R-back
            else if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) >= (-Math.PI)/2)) && rightLeft == true) {
                rightDriveF.setPower(-magnitude);
                rightDriveB.setPower(-magnitude);
            }

            else if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/x2) && Math.atan(gamepad1.right_stick_y/x2) >= (-Math.PI)/2)) && rightLeft == false) {
                rightDriveF.setPower(-magnitude);
                rightDriveB.setPower(-magnitude);
            }

            else {
                rightDriveF.setPower(0);
                rightDriveB.setPower(0);
            }


        }
    }
}

