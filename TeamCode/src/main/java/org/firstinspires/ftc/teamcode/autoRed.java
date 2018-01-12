package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Red", group = "Auto")
public class autoRed extends robot
{
    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(true); //Knocks the jewel off the platform

        strafe(-0.3, 30); //Hopefully strafes into position for the cipher read

        int position = position(); //Reads the cipher

        forward(1, 150); //Drive forward, out of the way of the cryptobox

        turn(1, -180); //Turn around 180 degrees to line up with the box

        strafe(1, 30);  //Approach the spot where point 0 on the cryptobox is

        strafe(1, cipher[position]);//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        placeBlock();



    }
}

