package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
    DcMotor leftDriveF, leftDriveB, rightDriveF, rightDriveB, elevator, elevator2, arm, flippyWinch, flippyFlip;
    ColorSensor color; // glyphReader;
    DistanceSensor sensorDistance, glyphReaderDistance;
    CRServo intakeRight, intakeLeft;
    Servo jewel, clawPivot, claw, jewelSwivel, flap, flapBL, flapBR, flapTR, flapTL;

    double downPosition = 1, upPosition = 0.5;
    double straightPosition = 0.19, rightPosition = 0, leftPosition = 0.8;

    //These are the values in mm of the close middle and far positions for placing the block from the starting point
    //0 = close, 1 = middle, 2 = far, 3 = nothing
    //Strafing for each program to the cryptobox
    int[] cipherBLUE = {50, 270, 545, 50};
    int[] cipherRED = {650, 300, 10, 10};
    int[] cipherBLUEBACK = {40, 320, 525, 0};
    int[] cipherREDBACK = {480, 250, 10, 10};

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    //Initializes robot
    public void init(int zed){
        //Initilization Procedures
        // Configuration in the phone, this allows the motors to control physical objects that the phone is connected to
        leftDriveF = hardwareMap.dcMotor.get("leftDriveF");
        leftDriveB = hardwareMap.dcMotor.get("leftDriveB");
        rightDriveF = hardwareMap.dcMotor.get("rightDriveF");
        rightDriveB = hardwareMap.dcMotor.get("rightDriveB");
        //elevator =  hardwareMap.dcMotor.get("elevator");
        //elevator2 = hardwareMap.dcMotor.get("elevator2");
        arm = hardwareMap.dcMotor.get("arm");

        flippyWinch = hardwareMap.dcMotor.get("flippyWinch");
        flippyFlip = hardwareMap.dcMotor.get("flippyFlip");

        flippyWinch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flippyFlip.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flapBL = hardwareMap.servo.get("flapBL");
        flapBR = hardwareMap.servo.get("flapBR");
        flapTR = hardwareMap.servo.get("flapTR");
        flapTL = hardwareMap.servo.get("flapTL");


        //intakeRight = hardwareMap.crservo.get("intakeRight");
        //intakeLeft = hardwareMap.crservo.get("intakeLeft");

        clawPivot = hardwareMap.servo.get("clawPivot");
        claw = hardwareMap.servo.get("claw");



        jewel = hardwareMap.servo.get("jewel");
        jewelSwivel = hardwareMap.servo.get("jewel_swivel");
        //flap = hardwareMap.servo.get("flap");

        rightDriveB.setDirection(DcMotorSimple.Direction.FORWARD);//ON LEFT SIDE FOR WHATEVER REASON
        rightDriveF.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDriveB.setDirection(DcMotorSimple.Direction.REVERSE);//ON RIGHT SIDE FOR WHATEVER REASON
        leftDriveF.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // get a reference to the color sensor
        color = hardwareMap.get(ColorSensor.class, "colorLeft");
        //glyphReader = hardwareMap.get(ColorSensor.class, "glyphReader");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colorLeft");
        //glyphReaderDistance =hardwareMap.get(DistanceSensor.class, "glyphReader");
        color.enableLed(false);
        //glyphReader.enableLed(false);

        //Configures the motors to automatically brake when they have no input
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    //Configures image tracking
    public VuforiaTrackable relicTemplate()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AcmoujL/////AAAAGfYN1VWHOESQp02jdVbkpgRIroIXb6bGJbVg+YmQNOR1Utps1uBrE31QT5LTDRtXTqfGsXa1UDAVYDCODNbSDvvBqaeL+InYfonHHdT5uSQCUlOM5KznGi0nxg87OadM5azVuy9kk+uc0w3lmN/8PDzgxO14VRINXAf3w5AkMzhZAhKbzOH3PXYD15b9WsxeBfgDLHahE3Utn1i5u4EYZwizxBCa2Kg4HvtuhNLPBW7qjAfU+VEEsXHXCsJXU16uPaSQoPGWQsgZF729eI7aKmFa/zImSqxi1LizI6Xx8GkLOINg9j+gOixUkF115rrI5Lg4in21bKiR51FR9WmTunV8e/gGPBPrcfGFRP77fzsa";


        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;//Configures which camera the program will use
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);//Uses those parameters we just configured

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");//Loads the picture options
        VuforiaTrackable relicTemplate = relicTrackables.get(0);// I think this continues to load the trackables
        relicTemplate.setName("relicVuMarkTemplate");// This gives it a name or something

        relicTrackables.activate();

        return relicTemplate;
    }
    //Uses image tracking in order to determine which position in the crypto box to place into
    //0= left, 1= center, 2= right, 3= unknown
    public int position()//You need to start from the left side of the cryptobox for this to work
    {
        VuforiaTrackable relicTemplate = relicTemplate();

        int i = 0;

        while(opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark == RelicRecoveryVuMark.CENTER) {
                telemetry.addData("VuMark", "Center");
                telemetry.update();
                return 1;
            } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
                telemetry.addData("VuMark", "Right");
                telemetry.update();
                return 2;
            } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                telemetry.addData("VuMark", "Left");
                telemetry.update();
                return 0;
            }
            else if (i == 3500)
            {
                break;
            }else {
                sleep(1);
                i++;
                telemetry.addData("VuMark", "not visible");
                telemetry.update();
            }

        }
        return 3;

    }

    //Code for placing a block
    public void placeBlock()
    {
        forward(0.5);
        sleep(1000);
        forward(0);
        openBottom(open);
        openBottom(open);
        sleep(500);
        forward(-0.5);
        sleep(500);
        forward(1);
        sleep(1000);
        forward(0);
        sleep(200);
        forward(-1);
        sleep(500);
        forward(0);
    }

    double close = 0.3, open = 0.5, openFull = 1;

    public void openTop(double amount)
    {
        flapTR.setPosition(amount);
        flapTL.setPosition(1 - amount);
    }
    public void openBottom(double amount)
    {
        flapBR.setPosition(amount);
        flapBL.setPosition(1 - amount);
    }

    //Hits off the jewel
    //If red == true, on red side, else on blue side
      public void jewel(boolean red)
    {
        if (red == true) //Wanted Red
        {
            jewel.setPosition(downPosition);
            sleep(1000);
            //If Red
            if (ColorTest(color) == 1)
            {
                jewelSwivel.setPosition(leftPosition);
                sleep(1000);
                jewelSwivel.setPosition(straightPosition);
                sleep(1000);
                jewel.setPosition(upPosition);
                sleep(1000);
            }
            //If blue
            else if (ColorTest(color) == 0)
            {
                jewelSwivel.setPosition(rightPosition);
                sleep(1000);
                jewelSwivel.setPosition(straightPosition);
                sleep(1000);
                jewel.setPosition(upPosition);
                sleep(1000);
            }
            //If none
            else if (ColorTest(color) == 0.5)
            {
                //Skip selective action
            }
            jewel.setPosition(upPosition);
            sleep(1000);
        }
        else //Wanted Blue
        {
            jewel.setPosition(downPosition);
            sleep(1000);
            //If Red
            if (ColorTest(color) == 1)
            {
                jewelSwivel.setPosition(rightPosition);
                sleep(1000);
                jewelSwivel.setPosition(straightPosition);
                sleep(1000);
                jewel.setPosition(upPosition);
                sleep(1000);
            }
            //If blue
            else if (ColorTest(color) == 0)
            {
                jewelSwivel.setPosition(leftPosition);
                sleep(1000);
                jewelSwivel.setPosition(straightPosition);
                sleep(1000);
                jewel.setPosition(upPosition);
                sleep(1000);
            }
            //If none
            else if (ColorTest(color) == 0.5)
            {
                //Skip selective action
            }
            jewel.setPosition(upPosition);
            sleep(1000);
        }
    }
    //Tests the color of the ball
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

    //Controls left drive train
    public void leftPower(double power)
    {
        leftDriveB.setPower(power);
        leftDriveF.setPower(power);
    }
    //Controls right drive train
    public void rightPower(double power)
    {
        rightDriveF.setPower(power);
        rightDriveB.setPower(power);
    }
    //Goes forward
    public void forward(double power)
    {
        leftPower(-power);
        rightPower(-power);
    }
    //Goes forward a specific amount
    public void forward(double power, int mm)
    {
        int pos = leftDriveF.getCurrentPosition();
        //Forward 1 meter to test encoders once we get the chance to use them

        if (power >= 0) {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                forward(power);
            }
        }
        else
        {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                forward(power);
            }
        }
        forward(0);
    }
    //Turns a specific angle
    //Positive Degrees to the right, negative to the left
    public void turn(double power, int degrees)
    {
        double diameter = 245 * 2; //245 mm radius
        int pos = leftDriveF.getCurrentPosition();
        double Circumfrence = Math.PI * diameter;
        double distance = (Math.abs(degrees) * Circumfrence) /180;
        telemetry.addData("First one:", "");
        telemetry.addData("Motor Value:", leftDriveB.getCurrentPosition());
        telemetry.update();

        if (degrees >= 0) {
            telemetry.addData("Left one:", "");
            telemetry.addData("Motor Value:", leftDriveB.getCurrentPosition());
            telemetry.update();
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(distance * 0.6))) {
                left(Math.abs(power));
                telemetry.addData("Left one cont:", "");
                telemetry.addData("Motor Value:", leftDriveB.getCurrentPosition());
                telemetry.update();
            }
        }
        else
        {telemetry.addData("Right one:", "");
            telemetry.addData("Motor Value:", leftDriveB.getCurrentPosition());
            telemetry.update();
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(distance * 0.6))) {
                telemetry.addData("Right one cont:", "");
                telemetry.addData("Motor Value:", leftDriveB.getCurrentPosition());
                telemetry.update();
                right(Math.abs(power));
            }
        }
        forward(0);
    }

    //Positive power is to the right, negative to the left. Strafes a specific distance
    public void strafe(double power, int mm)
    {
        int pos = leftDriveF.getCurrentPosition();
        //Forward 1 meter to test encoders once we get the chance to use them

        //Strafe Right
        if (power >= 0) {
            while (leftDriveF.getCurrentPosition() > (pos - mmtoticks(Math.abs(mm)))) {
                strafeRight(Math.abs(power));
            }
        }
        //Strafe left
        else {
            while (leftDriveF.getCurrentPosition() < (pos + mmtoticks(Math.abs(mm)))) {
                strafeLeft(Math.abs(power));
            }
        }
        forward(0);
    }

    //Strafes right
    public void strafeRight(double power)
    {
        leftDriveF.setPower(-power);
        leftDriveB.setPower(power);
        rightDriveF.setPower(-power);
        rightDriveB.setPower(power);
    }

    //Strafes left
    public void strafeLeft(double power)
    {
        leftDriveF.setPower(power);
        leftDriveB.setPower(-power);
        rightDriveF.setPower(power);
        rightDriveB.setPower(-power);
    }

    //Turns right
    public void right(double power)
    {
        leftPower(power);
        rightPower(-power);
    }
    //Turns left
    public void left(double power)
    {
        leftPower(-power);
        rightPower(power);
    }
// converts ticks for the encoders to mm
    public double tickstomm(int ticks){

        double mm = (ticks * (2));
        return mm;
    }
                                             //wheel gear ratio 1:2
    //Go forward for such and such distance
    public double mmtoticks(double mm)
    {
        double ticks = (mm / (2));
        return ticks;
    }

    public void runOpMode() throws InterruptedException{}

    //Updates the telemetry data of the robot so we can see what is going on with the sensors while driving
    public void telemetry ()
    {
        //get position
       /* telemetry.addData("Motor Positions", "");
        telemetry.addData("Right Back Motor Position: ", rightDriveB.getCurrentPosition());
        telemetry.addData("Right Front Motor Position: ", rightDriveF.getCurrentPosition());
        telemetry.addData("Left Back Motor Position: ", leftDriveB.getCurrentPosition());
        telemetry.addData("Left Front Motor Position: ", leftDriveF.getCurrentPosition());
        telemetry.addData("Up Down Servo Position:", clawPivot.getPosition());*/

        //telemetry.addData("Color", glyphReader.red());

       /* if( glyphReader.red() > 155)//This is a threshold that the color sensor has to get past in order to say there is a block in the chamber
        {
            telemetry.addData("YOU GOT THE BLOCK!!!!!!!!!!!!!!!!!", "");//This uses a color sensor in order to
            //Indicate to the drivers whether or not we grab a block in teleop.
        }
*/
        //telemetry.addData("Elevator Position: ", elevator.getCurrentPosition());
        //telemetry.addData("", "");
        //telemetry.update();

        //get power
        /*telemetry.addData("Motor Powers", "");
        telemetry.addData("Right Back Motor Power: ", rightDriveB.getPower());
        telemetry.addData("Right Front Motor Power: ", rightDriveF.getPower());
        telemetry.addData("Left Back Motor Power: ", leftDriveB.getPower());
        telemetry.addData("Left Front Motor Power: ", leftDriveF.getPower());
        telemetry.addData("", "");*/

        /*if (color.red() > color.blue())
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
        }*/
    }
}
