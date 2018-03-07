package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Red BACK", group = "Auto")
//@Disabled
public class autoRedBACK extends robot
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
        jewel(false); //Knocks the jewel off the platform

        int position = position(); //Reads the cipher

        forward(0.3, (int) 25.4* 29);//Towards Cryptobox

        forward(0);
        sleep(500);

        turn(0.3, -90);
        forward(0);
        sleep(500);

        forward(-0.5);
        sleep(500);
        forward(0);
        sleep(500);

        if (position == 0 || position == 3)
        {
            strafe(-0.3, cipherREDBACK[position] + ((int) (0)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        }
        else {

            strafe(-0.3, cipherREDBACK[position] + ((int) (4 * 25.4)));//Strafe into the correct ciphered position with the glyph, if the cipher was not read, go to position 0

        }

        forward(0);
        sleep(500);

        //turn(0.5, 20);

        /*if(position == 0)
        {
            forward(0.4);
            sleep(500);
            forward(0);
        }*/

        flippyWinch.setPower(-1);
        sleep(100);
        flippyWinch.setPower(0);
        sleep(500);

        placeBlock();

        forward(0);





    }
}

