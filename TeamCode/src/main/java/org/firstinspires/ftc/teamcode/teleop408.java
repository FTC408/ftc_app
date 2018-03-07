package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="TeleOp", group="Linear Opmode")



public class teleop408 extends robot {

    Boolean upDownClaw = true;//Will assist in the operations to raise the claw up or down
    Boolean inOutClaw = true;//Will assist in the operations to open or close the claw

    boolean openCloseTop = false; //False = open
    boolean openCloseBottom = false; //False = open

    boolean flippyCW = true;

    double clawPos = 0.5;

    @Override
    public void runOpMode() throws InterruptedException
    {
        init(0);

        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        while(opModeIsActive())
        {
            //flap.setPosition(0.15);
            modifiedMecanum();
            //elevatorControl();
            winchControl();
            clawControl();
            //intakeControl();
            newIntakeControl();

            if(gamepad1.b)
            {
                jewel.setPosition(1);
                jewelSwivel.setPosition(straightPosition);
            }
            else
            {
                jewel.setPosition(upPosition);
                jewelSwivel.setPosition(straightPosition);
            }


            //telemetry();
        }


    }

    public void newIntakeControl()
    {
        int CWposition = 130;
        int CCWposition = 0;
        int deltaPosition = CWposition - CCWposition;



        if(Math.abs(gamepad2.left_stick_y) > 0.1 )
        {
            flippyWinch.setPower(-1 * gamepad2.left_stick_y);
        }
        else
        {
            flippyWinch.setPower(0);
        }

        if (gamepad2.left_trigger > 0.2)
        {
            while (gamepad2.left_trigger > 0.2)
            {
                if (openCloseTop == false) //If open
                {
                    //Close top
                    flapTR.setPosition(0.1);
                    flapTL.setPosition(0.9);
                }
                else //If closed
                {
                    //Open top
                    flapTR.setPosition(0.5);
                    flapTL.setPosition(0.5);
                }

                //modifiedMecanum();
            }
            openCloseTop = !openCloseTop;
        }
        if (gamepad2.right_trigger > 0.2)
        {
            while (gamepad2.right_trigger > 0.2)
            {
                if (openCloseBottom == false) //If open
                {
                    //Close bottom
                    flapBR.setPosition(0.1);
                    flapBL.setPosition(0.9);
                }
                else //If closed
                {
                    //Open bottom
                    flapBR.setPosition(0.5);
                    flapBL.setPosition(0.5);
                }

                //modifiedMecanum();
            }
            openCloseBottom = !openCloseBottom;
        }
        if (gamepad2.b)
        {
            flapBL.setPosition(0);
            flapBR.setPosition(1);
            flapTL.setPosition(0);
            flapTR.setPosition(1);
        }

        /*if (gamepad2.left_bumper)
        {
            //Open top
            flapTR.setPosition(0.5);
            flapTL.setPosition(0.5);
        }
        if (gamepad2.right_bumper)
        {
            //Open Bottom
            flapBR.setPosition(0.5);
            flapBL.setPosition(0.5);
        }
        if (gamepad2.left_trigger > 0.2)
        {
            //Close Top
            flapTR.setPosition(0.3);
            flapTL.setPosition(0.7);
        }
        if (gamepad2.right_trigger > 0.2)
        {
            //Close Bottom
            flapBR.setPosition(0.3);
            flapBL.setPosition(0.7);
        }*/

        if (gamepad2.dpad_left)
        {
            flippyFlip.setPower(0.35);
            telemetry.addData("Flippy Position negative Power", flippyFlip.getCurrentPosition());
            telemetry.update();
        }
        else
        {
            flippyFlip.setPower(0);
        }
        //inc as cW
        if (gamepad2.dpad_right)
        {
            flippyFlip.setPower(-0.35);
            telemetry.addData("Flippy Position Positive Power", flippyFlip.getCurrentPosition());
            telemetry.update();
        }
        else
        {
            flippyFlip.setPower(0);
        }

        if (gamepad2.x)
        {
            while (gamepad2.x)
            {
                if (flippyCW == true)
                {
                    //Goes CCW
                    int pos = flippyFlip.getCurrentPosition();
                    int delta = pos - deltaPosition;
                    while (pos > delta)
                    {
                        flippyFlip.setPower(-0.5);
                        pos = flippyFlip.getCurrentPosition();
                        modifiedMecanum();
                    }
                    flippyFlip.setPower(0);
                }
                else
                {
                    //Goes clockwise
                    int pos = flippyFlip.getCurrentPosition();
                    int delta = pos + deltaPosition;
                    while (pos < delta)
                    {
                        flippyFlip.setPower(0.5);
                        pos = flippyFlip.getCurrentPosition();
                        modifiedMecanum();
                    }
                    flippyFlip.setPower(0);
                }
                modifiedMecanum();
            }
            flippyCW = !flippyCW;


        }

    }

    public void winchControl()
    {
        //The following controls the winch that moves the arm in or out
        if(Math.abs(gamepad2.right_stick_y) > 0.2 )
        {
            arm.setPower(gamepad2.right_stick_y);
        }
        else
        {
            arm.setPower(0);
        }
    }

    public void clawControl()
    {
        //If tapped will move claw in or out, the opposite of whatever it currently is
        if (gamepad2.a) {
            inOutClaw = !inOutClaw;
            sleep(200);
            if (inOutClaw == true)
            {
                claw.setPosition(0);
            }
            else
            {
                claw.setPosition(1);
            }
        }

        if (gamepad2.y)
        {
            clawPos = 0.5;
            clawPivot.setPosition(clawPos);
        }

        if (gamepad2.dpad_up)//0
        {
            clawPos = clawPos - 0.05;
            if (clawPos < 0)
            {
                clawPos = 0;
            }
            clawPivot.setPosition(clawPos);
            sleep(100);
        }
        if (gamepad2.dpad_down)//1
        {
            clawPos = clawPos + 0.05;
            if (clawPos > 1)
            {
                clawPos = 1;
            }
            clawPivot.setPosition(clawPos);
            sleep(100);
        }


    }

    public void elevatorControl()
    {
        // Control Vertical Elevator
        elevator.setPower(0);
        elevator2.setPower(0);
        if(Math.abs(gamepad2.left_stick_y) >= 0.2) //If up on left stick, elevator goes up, if down goes down
        {
            elevator.setPower(gamepad2.left_stick_y);
            elevator2.setPower(-gamepad2.left_stick_y);
        }
    }

    public void intakeControl() //Controls intake servos
    {
        if (gamepad2.right_bumper) //If right bumper, intake right
        {
            intakeRight.setPower(-1);
        }
        if (gamepad2.left_bumper) //If left bumper, intake left
        {
            intakeLeft.setPower(1);
        }

        if (gamepad2.right_trigger >= 0.2) //If right trigger is pressed, intake puushes out on right
        {
            intakeRight.setPower(1);
        }
        if (gamepad2.left_trigger >= 0.2) //If left trigger is pressed, intake pushes out on left
        {
            intakeLeft.setPower(-1);
        }
        if ((gamepad2.right_trigger < 0.2 && gamepad2.left_trigger < 0.2) && !gamepad2.right_bumper && !gamepad2.left_bumper) //If both are small enough, do nothing
        {
            intakeLeft.setPower(0);
            intakeRight.setPower(0);
        }
    }

   /* public void jewelArmControl()
    {
        if (gamepad2.a)
        {
            armRight.setPosition(0.75);
            armLeft.setPosition(0.4);
        }

        if (gamepad2.b)
        {
            armRight.setPosition(0.28);
            armLeft.setPosition(0.8);
        }
    }*/

    //This is the code that Drew made for 407, and as it is far more concise than anything I have dreamt up, we'll try it out
    public void megaModifiedMecanum()
    {
        double forward = gamepad1.right_stick_y;
        double side = gamepad1.right_stick_x;
        double rotate = gamepad1.left_stick_x;

        rightDriveF.setPower(Range.clip(forward+side-rotate, -1, 1));
        leftDriveF.setPower(Range.clip(forward-side+rotate, -1, 1));
        rightDriveB.setPower(Range.clip(forward-side-rotate, -1, 1));
        leftDriveB.setPower(Range.clip(forward+side+rotate, -1, 1));

    }


    //New control for the drive train
    public void modifiedMecanum()
    {
        if (Math.abs(gamepad1.left_stick_y) >= 0.2)
        {
            rightPower(gamepad1.left_stick_y);
        }

        if (Math.abs(gamepad1.left_stick_y) < 0.2 && gamepad1.right_trigger < 0.2 && gamepad1.left_trigger < 0.2) {
            rightPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) >= 0.2) {
            leftPower(gamepad1.right_stick_y);
        }
        if (Math.abs(gamepad1.right_stick_y) < 0.2 && gamepad1.right_trigger < 0.2 && gamepad1.left_trigger < 0.2)
        {
            leftPower(0);
        }
        if(gamepad1.right_trigger >= 0.2)
        {
            strafeRight(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger >= 0.2)
            strafeLeft(gamepad1.left_trigger);
    }


    //Controls the drivetrain
    public void mecanumDrive()
    {

        boolean toggle = true;

        if (gamepad1.b)
        {
            toggle = !toggle;
        }

        //Finds the "hypotenuse" if you will, of the triangle of x and y values that is created when the joystick is moved
        double magnitudeLeft = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));
        double magnitudeRight = Math.sqrt(Math.pow(gamepad1.right_stick_y,2)+Math.pow(gamepad1.right_stick_x,2));

        if (toggle == false)
        {
            magnitudeLeft /= 2;
            magnitudeRight /= 2;
        }

        //These help record whether x is positive or negative
        boolean left, right;


        if (gamepad1.left_stick_x < 0) //Records whether or not x is positive or negative. This helps the program decide
            left = false;  //Which quadrant the stick is in because the inverse tangent is only valid in the first and fourth
        else //quadrants. This boolean essentially lets us reflect over the y axis and compensate for how x messes things up
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

        //Inverse tangent approaches asymptotes at pi over two and negative pi over two, this gives that asymptote a definition
        //so that the robot won't simply stop at these values
        if (gamepad1.left_stick_x == 0)
        {
            leftDriveF.setPower(-magnitudeLeft * gamepad1.left_stick_y);
            leftDriveB.setPower(-magnitudeLeft * gamepad1.left_stick_y);
        }

        //L-right
        if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && left == true) {
            leftDriveF.setPower(magnitudeLeft);
            leftDriveB.setPower(-magnitudeLeft);
        }

        //L-left
        if (((Math.PI/4 > Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) && Math.atan(gamepad1.left_stick_y/gamepad1.left_stick_x) > (-Math.PI)/4)) && left == false) {
            leftDriveF.setPower(-magnitudeLeft);
            leftDriveB.setPower(magnitudeLeft);
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
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
        }

        if (((Math.PI/4 <= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) < (Math.PI)/2)) && right == false) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        //Inverse tangent approaches asymptotes at pi over two and negative pi over two, this gives that asymptote a definition
        //so that the robot won't simply stop at these values
        if (gamepad1.right_stick_x == 0)
        {
            rightDriveF.setPower(-magnitudeRight * gamepad1.right_stick_y);
            rightDriveB.setPower(-magnitudeRight * gamepad1.right_stick_y);
        }

        //R-right
        if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/4)) && right == true) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        //R-left
        if (((Math.PI/4 > Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/4)) && right == false) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
        }

        //R-back
        if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/2)) && right == true) {
            rightDriveF.setPower(magnitudeRight);
            rightDriveB.setPower(magnitudeRight);
        }

        if (((-Math.PI/4 >= Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) && Math.atan(gamepad1.right_stick_y/gamepad1.right_stick_x) > (-Math.PI)/2)) && right == false) {
            rightDriveF.setPower(-magnitudeRight);
            rightDriveB.setPower(-magnitudeRight);
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


}

