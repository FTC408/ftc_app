package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Austin on 11/14/2017.
 */

public class robot extends LinearOpMode
{
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator, elevator2, arm;
    ColorSensor color;
    DistanceSensor sensorDistance;
    CRServo intakeRight, intakeLeft;
    Servo jewel, clawPivot, claw;

    //These are the values in mm of the close middle and far positions for placing the block from the starting point
    //0 = close, 1 = middle, 2 = far, 3 = nothing
    int[] cipher = {0, 178, 356, 0};

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;


    public void init(int zed){
        //Initilization Procedures
        // Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");
        elevator =  hardwareMap.dcMotor.get("elevator");
        elevator2 = hardwareMap.dcMotor.get("elevator2");
        arm = hardwareMap.dcMotor.get("arm");

        intakeRight = hardwareMap.crservo.get("intakeRight");
        intakeLeft = hardwareMap.crservo.get("intakeLeft");

        clawPivot = hardwareMap.servo.get("clawPivot");
        claw = hardwareMap.servo.get("claw");

        jewel = hardwareMap.servo.get("jewel");

        rightDriveB.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDriveF.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // get a reference to the color sensor
        color = hardwareMap.get(ColorSensor.class, "colorLeft");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorLeft");
        color.enableLed(false);

        //Configures the motors to automatically brake when they have no input
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Sets the jewel arm position to the best spot
        jewel.setPosition(0.28);
    }

    public VuforiaTrackable relicTemplate()
    {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();//Configures Vuforia parameters

        parameters.vuforiaLicenseKey = "AcmoujL/////AAAAGfYN1VWHOESQp02jdVbkpgRIroIXb6bGJbVg+YmQNOR1Utps1uBrE31QT5LTDRtXTqfGsXa1UDAVYDCODNbSDvvBqaeL+InYfonHHdT5uSQCUlOM5KznGi0nxg87OadM5azVuy9kk+uc0w3lmN/8PDzgxO14VRINXAf3w5AkMzhZAhKbzOH3PXYD15b9WsxeBfgDLHahE3Utn1i5u4EYZwizxBCa2Kg4HvtuhNLPBW7qjAfU+VEEsXHXCsJXU16uPaSQoPGWQsgZF729eI7aKmFa/zImSqxi1LizI6Xx8GkLOINg9j+gOixUkF115rrI5Lg4in21bKiR51FR9WmTunV8e/gGPBPrcfGFRP77fzsa";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;//Configures which camera the program will use
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);//Uses those parameters we just configured

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");//Loads the picture options
        VuforiaTrackable relicTemplate = relicTrackables.get(0);// I think this continues to load the trackables
        relicTemplate.setName("relicVuMarkTemplate");// This gives it a name or something

        return relicTemplate;
    }

    //0= left, 1= center, 2= right, 3= unknown
    public int position()//You need to start from the left side of the cryptobox for this to work
    {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate());
        sleep(2000);

        if (vuMark == RelicRecoveryVuMark.CENTER)
        {
            telemetry.addData("VuMark", "Center");
            return 1;
        }
        else if (vuMark == RelicRecoveryVuMark.RIGHT)
        {
            telemetry.addData("VuMark", "Right");
            return 2;
        }
        else if (vuMark == RelicRecoveryVuMark.LEFT)
        {
            telemetry.addData("VuMark", "Left");
            return 0;
        }
        else{
            telemetry.addData("VuMark", "not visible");
            return 3;
        }
    }

    //Code for placing a block
    public void placeBlock()
    {
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1500);
        intakeLeft.setPower(0);
        intakeRight.setPower(0);
    }

    //If red == true, on red side, else on blue side
      public void jewel(boolean red)
    {
        if (red == true) //Wanted Red
        {
            jewel.setPosition(0.75);
            sleep(1000);
            //If Red
            if (ColorTest(color) == 1)
            {
                forward(0.3, 50);
                forward(0); sleep(500);
                jewel.setPosition(0.28);
                sleep(1000);
                forward(-0.3, 50);
                forward(0); sleep(500);
                //Away from color sensor
            }
            //If blue
            else if (ColorTest(color) == 0)
            {
                forward(-0.3, 50);
                forward(0); sleep(500);
                jewel.setPosition(0.28);
                sleep(1000);
                forward(0.3, 50);
                forward(0); sleep(500);
                //Towards color sensor
            }
            //If none
            else if (ColorTest(color) == 0.5)
            {
                //Skip selective action
            }
            jewel.setPosition(0.28);
            sleep(1000);
        }
        else //Wanted Blue
        {
            jewel.setPosition(0.4);
            sleep(1000);
            //If Red
            if (ColorTest(color) == 1)
            {
                forward(-0.3, 60);
                forward(0); sleep (500);
                jewel.setPosition(0.8);
                sleep(1000);
                forward(0.3, 60);
                forward(0); sleep(500);
                //Away from color sensor
            }
            //If blue
            else if (ColorTest(color) == 0)
            {
                forward(0.3, 60);
                forward(0); sleep(500);
                jewel.setPosition(0.8);
                sleep(1000);
                forward(-0.3, 60);
                forward(0); sleep(500);
                //Towards color sensor
            }
            //If none
            else if (ColorTest(color) == 0.5)
            {
                //Skip selective action
            }
            jewel.setPosition(0.8);
            sleep(1000);
        }
    }
    //Returns 1 if red, 0 if blue, 0.5 if neither
    public double ColorTest(ColorSensor color)
    {
        if (color.red() > color.blue())
        {
            return 1;
        }
        if (color.blue() > color.red())
        {
            return 0;
        }
        return 0.5;
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

    //Positive Degrees to the right, negative to the left
    public void turn(double power, int degrees)
    {
        double diameter = 245 * 2; //245 mm radius
        int pos = leftDriveF.getCurrentPosition();
        double Circumfrence = Math.PI * diameter;
        double distance = (Math.abs(degrees) * Circumfrence) /180;

        if (degrees >= 0) {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(distance))) {
                right(Math.abs(power));
            }
        }
        else
        {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(distance))) {
                left(Math.abs(power));
            }
        }
    }

    //Positive power is to the right, negative to the left
    public void strafe(double power, int mm)
    {
        int pos = leftDriveF.getCurrentPosition();
        //Forward 1 meter to test encoders once we get the chance to use them

        //Strafe Right
        if (power >= 0) {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                strafeRight(Math.abs(power));
            }
        }
        //Strafe left
        else {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                strafeLeft(Math.abs(power));
            }
        }
    }

    public void strafeRight(double power)
    {
        leftDriveF.setPower(-power);
        leftDriveB.setPower(power);
        rightDriveF.setPower(-power);
        rightDriveB.setPower(power);
    }

    public void strafeLeft(double power)
    {
        leftDriveF.setPower(power);
        leftDriveB.setPower(-power);
        rightDriveF.setPower(power);
        rightDriveB.setPower(-power);
    }

    public void right(double power)
    {
        leftPower(power);
        rightPower(-power);
    }
    public void left(double power)
    {
        leftPower(-power);
        rightPower(power);
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

    public void runOpMode() throws InterruptedException{}

    //Updates the telemetry data of the robot so we can see what is going on with the sensors while driving
    public void telemetry ()
    {
        //get position
        telemetry.addData("Motor Positions", "");
        telemetry.addData("Right Back Motor Position: ", rightDriveB.getCurrentPosition());
        telemetry.addData("Right Front Motor Position: ", rightDriveF.getCurrentPosition());
        telemetry.addData("Left Back Motor Position: ", leftDriveB.getCurrentPosition());
        telemetry.addData("Left Front Motor Position: ", leftDriveF.getCurrentPosition());
        telemetry.addData("Elevator Position: ", elevator.getCurrentPosition());
        telemetry.addData("", "");

        //get power
        telemetry.addData("Motor Powers", "");
        telemetry.addData("Right Back Motor Power: ", rightDriveB.getPower());
        telemetry.addData("Right Front Motor Power: ", rightDriveF.getPower());
        telemetry.addData("Left Back Motor Power: ", leftDriveB.getPower());
        telemetry.addData("Left Front Motor Power: ", leftDriveF.getPower());
        telemetry.addData("", "");

        if (color.red() > color.blue())
        {
            telemetry.addData("Color Left: ", "Red");
        }
        if (color.red() < color.blue())
        {
            telemetry.addData("Color Left: ", "Blue");
        }
        if (color.red() == color.blue())
        {
            telemetry.addData("Color Left: ", "Neither");
        }
        telemetry.update();
    }
}
