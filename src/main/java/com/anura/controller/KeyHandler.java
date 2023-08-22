package com.anura.controller;

import com.anura.view.GamePanel;
import com.anura.view.Music;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {  // won't use this
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns the number of the key that was pressed
        // Title State
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0) {
                    gp.ui.menuNum = 3;
                }
                gp.ui.drawTitleScreen();
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 3) {
                    gp.ui.menuNum = 0;
                }
                gp.ui.drawTitleScreen();
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.menuNum) {
                    case 0:
                        gp.gameState = gp.playState;
                        Music.playBGMusic("ShumbaTest.wav");
                        gp.ui.musicPlaying = true;
                        break;
                    case 1:
                        // not implemented yet
                        break;
                    case 2:
                        // play console game
                        /*  Not working in JAR yet
                        GameLogic gameLogic = new GameLogic();
                        try {
                            gameLogic.execute();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (URISyntaxException ex) {
                            ex.printStackTrace();
                        }

                         */
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
            }
        }

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            // if key is pressed, set to true
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            switch (code) {
                case KeyEvent.VK_ESCAPE:
                    gp.gameState = gp.playState;
                    break;
                case KeyEvent.VK_W:
                    gp.ui.pauseNum--;
                    if (gp.ui.pauseNum < 0) {
                        gp.ui.pauseNum = 3;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.pauseNum++;
                    if (gp.ui.pauseNum > 3) {
                        gp.ui.pauseNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    switch (gp.ui.pauseNum) {
                        case 0:
                            // help function
                            gp.gameState = gp.helpState;
                            break;
                        case 1:
                            // music options
                            gp.gameState = gp.soundState;
                            break;
                        case 2:
                            // save game option
                            break;
                        case 3:
                            // quit game
                            System.exit(0);
                            break;
                    }
                    break;
            }
        }
        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER ||
                    code == KeyEvent.VK_W ||
                    code == KeyEvent.VK_D ||
                    code == KeyEvent.VK_A ||
                    code == KeyEvent.VK_S) {
                gp.gameState = gp.playState;
            }
            // Help State
        } else if (gp.gameState == gp.helpState) {
            if (code == KeyEvent.VK_ENTER ||
                    code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        // SOUND STATE
        else if (gp.gameState == gp.soundState) {
            switch (code) {
                case KeyEvent.VK_ESCAPE:
                    gp.gameState = gp.playState;
                    break;
                case KeyEvent.VK_W:
                    gp.ui.soundNum--;
                    if (gp.ui.soundNum < 0) {
                        gp.ui.soundNum = 3;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.soundNum++;
                    if (gp.ui.soundNum > 3) {
                        gp.ui.soundNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    String bgFilepath = "ShumbaTest.wav";
                    switch (gp.ui.soundNum) {
                        case 0:
                            // music off function (only if already on)
                            if (gp.ui.musicPlaying) {
                                Music.stopBackgroundMusic();
                                gp.ui.musicPlaying = false;
                            }
                            break;
                        case 1:
                            // music on (only if already off)
                            if (!gp.ui.musicPlaying) {
                                Music.playBGMusic(bgFilepath);

                                gp.ui.musicPlaying = true;
                            }
                            break;
                        case 2:
                            // volume up
                            if (gp.ui.musicPlaying) {
                                if (Music.getVolume() <= 1.0f) {
                                    Music.stopBackgroundMusic();
                                    Music.increaseVolume();
                                    Music.playBGMusic(bgFilepath);
                                } else {
                                    Music.setBGMVolume(1.0F);
                                }
                            }
                            break;
                        case 3:
                            // volume down
                            if (gp.ui.musicPlaying) {
                                if (Music.getVolume() >= 0.1f) {
                                    Music.stopBackgroundMusic();
                                    Music.decreaseVolume();
                                    Music.playBGMusic(bgFilepath);
                                } else {
                                    Music.setBGMVolume(0.1F);
                                }
                            }
                            break;
                    }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // when key released, set to false
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
    }
}
