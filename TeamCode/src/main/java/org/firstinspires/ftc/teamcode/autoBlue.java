package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue", group = "Auto")
//@Disabled
public class autoBlue extends robot
{



    public void runOpMode() throws InterruptedException
    {
        init(0);

        waitForStart();

        //EVERYTHING BELOW IS GUESSTIMATION OF MEASUREMENTS AND POWERS, THEY WILL NEED TO CHANGE BUT THE GENERAL STRUCTURE OF THE PROGRAM WILL NOT, TEST THE CRAP OUT OF THIS TOMORROW
        int position = position(); //Reads the cipher
        jewel(false); //Knocks the jewel off the platform

        forward(-0.3, (int)(25.5*29));

        forward(0);
        sleep(1000);

        turn(0.3, 180);

        forward(0);
        sleep(1000);

        strafe(0.3, cipherBLUE[position] +  ((int)(2*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        forward(0);
        sleep(1000);

        placeBlock();

        intakeLeft.setPower(1);
        intakeRight.setPower(-1);

        forward(0.6);
        sleep(1000);
        forward(0);
        sleep(1000);
        forward(-0.4);
        sleep(1000);
        forward(0);
        sleep(1000);
        intakeLeft.setPower(0);
        intakeRight.setPower(0);




    }
}

