package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue BACK", group = "Auto")
//@Disabled
public class autoBlueBACK extends robot
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

        forward(-0.3, (int)(25.5*25.5));

        sleep(500);

        turn(0.3, -90);

        forward(0);
        sleep(500);

        //forward(0.5, 0);

        sleep(500);

        if (position == 0)
        {
            strafe(0.5, cipherBLUEBACK[position] +  ((int)(0)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        }

        strafe(0.5, cipherBLUEBACK[position] +  ((int)(1*25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        forward(0);
        sleep(500);

        if(position != 2)
        {
            forward(0.5, 50);
            sleep(1000);
        }


        placeBlock();

        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1000);
        forward(0.6);
        sleep(1000);
        forward(0);
        sleep(1000);
        forward(-0.4);
        sleep(1100);
        forward(0);
        sleep(2000);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);




    }
}

