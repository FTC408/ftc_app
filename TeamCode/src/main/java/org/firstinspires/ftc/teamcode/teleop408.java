package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="TeleOp", group="Linear Opmode")
public class teleop408 extends LinearOpMode {
    // Here is a change
    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator;

    double ticksPerRev = 288;
    double gearRatio = 1.33;
    double diameter = 101.6;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //Initilization Procedures
        //Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");
      //  elevator =  hardwareMap.dcMotor.get("elevator");

        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        leftDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        while (opModeIsActive())
        {



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
            if (((Math.PI/4 <= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == true) {
                leftDriveF.setPower(-magnitudeLeft);
                leftDriveB.setPower(-magnitudeLeft);
            }

            if (((Math.PI/4 <= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == false) {
                leftDriveF.setPower(magnitudeLeft);
                leftDriveB.setPower(magnitudeLeft);
            }

            if (gamepad1.left_stick_y == 1 || gamepad1.left_stick_y == -1)
            {
                leftDriveF.setPower(-magnitudeLeft * gamepad1.left_stick_y);
                leftDriveB.setPower(-magnitudeLeft * gamepad1.left_stick_y);
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
            if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/2)) && left == true) {
                leftDriveF.setPower(magnitudeLeft);
                leftDriveB.setPower(magnitudeLeft);
            }

            if (((-Math.PI/4 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/2)) && left == false) {
                leftDriveF.setPower(-magnitudeLeft);
                leftDriveB.setPower(-magnitudeLeft);
            }

            //R-forward
            if (((Math.PI/4 <= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == true) {
                rightDriveF.setPower(magnitudeRight);
                rightDriveB.setPower(magnitudeRight);
            }

            if (((Math.PI/4 <= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == false) {
                rightDriveF.setPower(-magnitudeRight);
                rightDriveB.setPower(-magnitudeRight);
            }

            if (gamepad1.right_stick_y == 1 || gamepad1.right_stick_y == -1)
            {
                rightDriveF.setPower(magnitudeRight * gamepad1.right_stick_y);
                rightDriveB.setPower(magnitudeRight * gamepad1.right_stick_y);
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
            if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/2)) && right == true) {
                rightDriveF.setPower(-magnitudeRight);
                rightDriveB.setPower(-magnitudeRight);
            }

            if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/2)) && right == false) {
                rightDriveF.setPower(magnitudeRight);
                rightDriveB.setPower(magnitudeRight);
            }

            //Automatically sets the power of the motors to zero if none of the stuff below is true. This should solve a problem we
            //Were having on 11/1/2017 where there was a lag between when we would do something on the controller and
            //When the robot would do it. I think this was because of how if else statements tend to be... binary in nature
            //And maybe it took the controller a while to catch up.
            if (Math.abs(gamepad1.left_stick_x) < 0.2 && Math.abs(gamepad1.left_stick_y) < 0.2)
            {
                leftDriveB.setPower(0);
                leftDriveF.setPower(0);
            }

            if (Math.abs(gamepad1.right_stick_x) < 0.2 && Math.abs(gamepad1.right_stick_y) < 0.2)
            {
                rightDriveF.setPower(0);
                rightDriveB.setPower(0);
            }

//            if(gamepad1.dpad_up)
//            {
//                elevator.setPower(1);
//
//            }
//
//            if(gamepad1.dpad_down)
//            {
//                elevator.setPower(-1);
//
//            }
//

            telemetry();


        }
    }

    public void telemetry ()
    {
        //get position
        telemetry.addData("Right Back Motor Position: ", rightDriveB.getCurrentPosition());
        telemetry.addData("Right Front Motor Position: ", rightDriveF.getCurrentPosition());
        telemetry.addData("Left Back Motor Position: ", leftDriveB.getCurrentPosition());
        telemetry.addData("Left Front Motor Position: ", leftDriveF.getCurrentPosition());

        //get power
        telemetry.addData("Right Back Motor Power: ", rightDriveB.getPower());
        telemetry.addData("Right Front Motor Power: ", rightDriveF.getPower());
        telemetry.addData("Left Back Motor Power: ", leftDriveB.getPower());
        telemetry.addData("Left Front Motor Power: ", leftDriveF.getPower());

        telemetry.update();


    }
}

