package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue FAST", group = "Auto")
@Disabled
public class autoBlueFAST extends robotFAST
{



    public void runOpMode() throws InterruptedException
    {
        init(0);

        //Sets the jewel arm position to the best spot
        jewelSwivel.setPosition(straightPosition);
        jewel.setPosition(upPosition);
        waitForStart();

        //EVERYTHING BELOW IS GUESSTIMATION OF MEASUREMENTS AND POWERS, THEY WILL NEED TO CHANGE BUT THE GENERAL STRUCTURE OF THE PROGRAM WILL NOT, TEST THE CRAP OUT OF THIS TOMORROW
        int position = position(); //Reads the cipher
        jewel(true); //Knocks the jewel off the platform

        forward(-0.5, (int)(25.5*25.5));

        sleep(500);

        turn(0.5, 165);

        forward(0);
        sleep(500);

        strafe(0.5, cipherBLUE[position] +  ((int)(2*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        forward(0);
        sleep(500);

        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1000);
        forward(0.6);
        sleep(1000);
        forward(0);
        sleep(1000);
        forward(-0.4);
        sleep(1200);
        forward(0);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);




    }
}

