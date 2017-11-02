package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="mecanum", group="Linear Opmode")
public class mecanumTest extends LinearOpMode {

    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB;

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

        while (opModeIsActive())
        {
            //Automatically sets the power of the motors to zero if none of the stuff below is true. This should solve a problem we
            //Were having on 11/1/2017 where there was a lag between when we would do something on the controller and
            //When the robot would do it. I think this was because of how if else statements tend to be... binary in nature
            //And maybe it took the controller a while to catch up.
            rightDriveF.setPower(0);
            rightDriveB.setPower(0);
            leftDriveB.setPower(0);
            leftDriveF.setPower(0);

            //Finds the "hypotenuse" if you will, of the triangle of x and y values that is created when the joystick is moved
            double magnitudeLeft = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));
            double magnitudeRight = Math.sqrt(Math.pow(gamepad1.right_stick_y,2)+Math.pow(gamepad1.right_stick_x,2));

            boolean left, right;

            if (gamepad1.left_stick_x < 0) //Records whether or not x is positive or negative. This helps the program decide
                left = false;  //Which quadrant the stick is in
            else
                left = true;


            if (gamepad1.right_stick_x < 0) //Records whether or not x is positive or negative. This helps the program decide
                right = false;  //Which quadrnat the stick is in
            else
                right = true;


            //L-forward
            if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == true) {
                leftDriveF.setPower(magnitudeLeft);
                leftDriveB.setPower(magnitudeLeft);
            }

             if (((Math.PI/4 < Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == false) {
                leftDriveF.setPower(magnitudeLeft);
                leftDriveB.setPower(magnitudeLeft);
            }

            //L-right
             if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && left == true) {
                leftDriveF.setPower(-magnitudeLeft);
                leftDriveB.setPower(magnitudeLeft);
            }

            //L-left
             if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && left == false) {
                leftDriveF.setPower(magnitudeLeft);
                leftDriveB.setPower(-magnitudeLeft);
            }

            //L-back
             if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/2)) && left == true) {
                leftDriveF.setPower(-magnitudeLeft);
                leftDriveB.setPower(-magnitudeLeft);
            }

             if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/2)) && left == false) {
                leftDriveF.setPower(-magnitudeLeft);
                leftDriveB.setPower(-magnitudeLeft);
            }

            //R-forward
            if (((Math.PI/4 < Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == true) {
                rightDriveF.setPower(magnitudeRight);
                rightDriveB.setPower(magnitudeRight);
            }

            if (((Math.PI/4 < Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == false) {
                rightDriveF.setPower(magnitudeRight);
                rightDriveB.setPower(magnitudeRight);
            }

            //R-right
            if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/4)) && right == true) {
                rightDriveF.setPower(magnitudeRight);
                rightDriveB.setPower(-magnitudeRight);
            }

            //R-left
            if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/4)) && right == false) {
                rightDriveF.setPower(-magnitudeRight);
                rightDriveB.setPower(magnitudeRight);
            }

            //R-back
            if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/2)) && right == true) {
                rightDriveF.setPower(-magnitudeRight);
                rightDriveB.setPower(-magnitudeRight);
            }

            if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/2)) && right == false) {
                rightDriveF.setPower(-magnitudeRight);
                rightDriveB.setPower(-magnitudeRight);
            }
        }
    }
}

