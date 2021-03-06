package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Red FAST", group = "Auto")
@Disabled
public class autoRedFAST extends robotFAST
{
    public void runOpMode() throws InterruptedException
    {
        init(0);
        //Sets the jewel arm position to the best spot
        jewelSwivel.setPosition(straightPosition);
        jewel.setPosition(upPosition);
        waitForStart();

        int position = position(); //Reads the cipher
        jewel(false); //Knocks the jewel off the platform



        forward(0.5, (int) 25.4* 26);//Towards Cryptobox

        forward(0);
        sleep(500);

        strafe(-0.5, cipherRED[position] +  ((int)(3*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        forward(0);
        sleep(1000);

        //turn(0.5, 20);

        /*if(position == 0)
        {
            forward(0.4);
            sleep(500);
            forward(0);
        }*/
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1000);

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

        strafe(-0.5, ((int)(10*25.4)));
        turn(1, 120);

        extraGlyph(true);




    }
}

