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

        //Sets the jewel arm position to the best spot
        jewelSwivel.setPosition(straightPosition);
        jewel.setPosition(upPosition);
        waitForStart();

        openBottom(close);
        sleep(200);
        flippyWinch.setPower(1);
        sleep(700);
        flippyWinch.setPower(0);
        //EVERYTHING BELOW IS GUESSTIMATION OF MEASUREMENTS AND POWERS, THEY WILL NEED TO CHANGE BUT THE GENERAL STRUCTURE OF THE PROGRAM WILL NOT, TEST THE CRAP OUT OF THIS TOMORROW
        jewel(true); //Knocks the jewel off the platform

        int position = position(); //Reads the cipher

        forward(-0.3, (int)(25.5*24));

        sleep(500);

        turn(0.3, 165);

        forward(0);
        sleep(200);

        forward(-0.7, 50);

        forward(0);
        sleep(500);

        if (position == 0)
        {
            strafe(0.5, cipherBLUE[position] );//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        }
        else
        {
            strafe(0.5, cipherBLUE[position] +  ((int)(1*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        }

        forward(0);
        sleep(200);

        flippyWinch.setPower(-1);
        sleep(100);
        flippyWinch.setPower(0);
        sleep(500);

        placeBlock();

        /*intakeLeft.setPower(0);
        intakeRight.setPower(0);
        sleep(1000);
        forward(0.6);
        sleep(1000);
        forward(0);
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1000);
        forward(-0.4);
        sleep(1100);
        forward(0);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);*/




    }
}

