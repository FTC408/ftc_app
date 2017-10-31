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
            //Assign values to the doubles using the gamepad values
            left = -gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            //Assign power values to the motors, using the values we just made with the gamepad lines
            leftDriveF.setPower(left);
            leftDriveB.setPower(left);
            rightDriveF.setPower(right);
            rightDriveB.setPower(right);

            double magnitude = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));

            double x = gamepad1.left_stick_x;
            boolean leftRight;

            if (x < 0)
            {
                x = -x;
                leftRight = false;
            }
            else
            {
                leftRight = true;
            }
            //forward
            if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && leftRight == true) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(magnitude);
            }

             if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && leftRight == false) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(magnitude);
            }

            //right
             if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && leftRight == true) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(magnitude);
            }

            //left
             if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && leftRight == false) {
                leftDriveF.setPower(magnitude);
                leftDriveB.setPower(-magnitude);
            }

            //back
             if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/2)) && leftRight == true) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(-magnitude);
            }

             if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/2)) && leftRight == false) {
                leftDriveF.setPower(-magnitude);
                leftDriveB.setPower(-magnitude);
            }

            else
                leftDriveF.setPower(0);
                leftDriveB.setPower(0);


        }
    }
}

