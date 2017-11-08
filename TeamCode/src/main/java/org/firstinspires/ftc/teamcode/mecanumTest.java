package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="Mecanum", group="Linear Opmode")
public class mecanumTest extends LinearOpMode {
    // Here is a change
    //Create variables and hardware
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator;

    double ticksPerRev = 288, gearRatio = 1.33, diameter = 101.6;

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
            mecanumDrive(); //Controls the drivetrain. See mecanumDrive method below. This is here for the sake of navigational simplicity. I don't think
            //that the code for mecanum navigation is going to change all that much so I don't really need to see it unless I
            //want to. Also it is easier to not get if statements mixed up this way, as it can be hard to tell where the control
            //For one thing ends and another begins


            // Control Vertical Elevator
//            if(gamepad1.dpad_up) //If the up button is pressed, the elevator will go up
//            {
//                elevator.setPower(1);
//
//            }
//
//            if(gamepad1.dpad_down) //If the down button is pressed, the elevator will go down
//            {
//                elevator.setPower(-1);
//
//            }
//
            telemetry(); //Updates telemetry. See telemetry method below

        }
    }

    //Controls the drivetrain
    public void mecanumDrive()
    {
        //Finds the "hypotenuse" if you will, of the triangle of x and y values that is created when the joystick is moved
        double magnitudeLeft = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));
        double magnitudeRight = Math.sqrt(Math.pow(gamepad1.right_stick_y,2)+Math.pow(gamepad1.right_stick_x,2));

        //These help record whether x is positive or negative
        boolean left, right;


        if (gamepad1.left_stick_x < 0) //Records whether or not x is positive or negative. This helps the program decide
            left = false;  //Which quadrant the stick is in because the inverse tangent is only valid in the first and fourth
        else //quadrants. This boolean essentially lets us reflect over the y axis and compensate for how x screws things up
            left = true;


        if (gamepad1.right_stick_x < 0) //Records whether or not x is positive or negative. This helps the program decide
            right = false;  //Which quadrnat the stick is in
        else
            right = true;


        //L-forward
        if (((Math.PI/3 <= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == true) {
            leftDriveF.setPower(-magnitudeLeft);
            leftDriveB.setPower(-magnitudeLeft);
        }

        if (((Math.PI/3 <= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) < (Math.PI)/2)) && left == false) {
            leftDriveF.setPower(magnitudeLeft);
            leftDriveB.setPower(magnitudeLeft);
        }

        //L-right
        if (((Math.PI/6 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/6)) && left == true) {
            leftDriveF.setPower(-magnitudeLeft);
            leftDriveB.setPower(magnitudeLeft);
        }

        //L-left
        if (((Math.PI/6 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/6)) && left == false) {
            leftDriveF.setPower(magnitudeLeft);
            leftDriveB.setPower(-magnitudeLeft);
        }

        //L-back
        if (((-Math.PI/3 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >=(-Math.PI)/2)) && left == true) {
            leftDriveF.setPower(magnitudeLeft);
            leftDriveB.setPower(magnitudeLeft);
        }

        if (((-Math.PI/3 >= Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) >= (-Math.PI)/2)) && left == false) {
            leftDriveF.setPower(-magnitudeLeft);
            leftDriveB.setPower(-magnitudeLeft);
        }

        //R-forward
        if (((Math.PI/3 <= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == true) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        if (((Math.PI/3 <= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == false) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
        }

        //R-right
        if (((Math.PI/6 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/6)) && right == true) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
        }

        //R-left
        if (((Math.PI/6 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/6)) && right == false) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        //R-back
        if (((-Math.PI/3 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/2)) && right == true) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
        }

        if (((-Math.PI/3 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) >= (-Math.PI)/2)) && right == false) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        //Left Forward Diagonal-right
        if (((Math.PI/3 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (Math.PI)/6)) && left == true) {
            leftDriveF.setPower(magnitudeLeft);
            leftDriveB.setPower(0);
        }

        //Left Forward Diagonal-left
        if (((Math.PI/3 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (Math.PI)/6)) && left == false) {
            leftDriveF.setPower(0);
            leftDriveB.setPower(magnitudeLeft);
        }

        //Left Diagonal-back right
        if (((-Math.PI/6 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/3) && left == true)) {
            leftDriveF.setPower(0);
            leftDriveB.setPower(-magnitudeLeft);
        }

        //Left Diagonal back left
        if (((-Math.PI/6 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/3)) && left == false) {
            leftDriveF.setPower(-magnitudeLeft);
            leftDriveB.setPower(0);
        }
        //Right Diagonal-forward right
        if (((Math.PI/6 < Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/3)) && right == true) {
            rightDriveF.setPower(0);
            rightDriveB.setPower(magnitudeRight);
        }

        //Right Diagonal forward left
        if (((Math.PI/6 < Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/3)) && right == false) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(0);
        }

        //Right Diagonal-back (Right)
        if (((-Math.PI/6 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/3)) && right == true) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(0);
        }

        //Right Diagonal-back (Left)
        if (((-Math.PI/6 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/3)) && right == false) {
            rightDriveF.setPower(0);
            rightDriveB.setPower(-magnitudeRight);
        }



        //Inverse tangent approaches asymptotes at pi over two and negative pi over two, this gives that asymptote a definition
        //so that the robot won't simply stop at these values
        if (gamepad1.right_stick_x == 0)
        {
            rightDriveF.setPower(magnitudeRight * gamepad1.right_stick_y);
            rightDriveB.setPower(magnitudeRight * gamepad1.right_stick_y);
        }

        //Inverse tangent approaches asymptotes at pi over two and negative pi over two, this gives that asymptote a definition
        //so that the robot won't simply stop at these values
        if (gamepad1.left_stick_x == 0)
        {
            leftDriveF.setPower(-magnitudeLeft * gamepad1.left_stick_y);
            leftDriveB.setPower(-magnitudeLeft * gamepad1.left_stick_y);
        }


        //This sets the power of the motors to zero if the value that they are being driven by is less than 0.2
        //This allows the robot to stop when it is receiving minimal input
        if (magnitudeLeft < 0.2)
        {
            leftDriveB.setPower(0);
            leftDriveF.setPower(0);
        }

        if (magnitudeRight < 0.2)
        {
            rightDriveF.setPower(0);
            rightDriveB.setPower(0);
        }
    }

    //Updates the telemetry data of the robot so we can see what is going on with the sensors while driving
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

