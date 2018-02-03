package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Red", group = "Auto")
//@Disabled
public class autoRed extends robot
{
    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(false); //Knocks the jewel off the platform

        int position = position(); //Reads the cipher

        forward(0.3, (int) 25.4* 29);//Towards Cryptobox

        forward(0);
        sleep(1000);

        strafe(-0.3, cipherRED[position] +  ((int)(2*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        forward(0);
        sleep(1000);

        //turn(0.5, 20);

        /*if(position == 0)
        {
            forward(0.4);
            sleep(500);
            forward(0);
        }*/

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

