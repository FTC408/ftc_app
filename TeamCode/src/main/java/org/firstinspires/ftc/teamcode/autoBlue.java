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
@Disabled
public class autoBlue extends robot
{

    public void runOpMode() throws InterruptedException
    {
        init(0);

        waitForStart();

        jewel(false); //Knocks the jewel off the platform

        strafe(0.3, 30); //Hopefully strafes into position for the cipher read

        int position = position(); //Reads the cipher

        forward(1, 150); //Drive forward, out of the way of the cryptobox

        turn(1, 180); //Turn around 180 degrees to line up with the box

        strafe(-1, 30);  //Approach the spot where point 0 on the cryptobox is

        strafe(-1, cipher[position]);//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        placeBlock();




    }
}

