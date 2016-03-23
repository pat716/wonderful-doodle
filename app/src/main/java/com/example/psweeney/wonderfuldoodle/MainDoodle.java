package com.example.psweeney.wonderfuldoodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainDoodle extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private enum ActiveState{
        DOODLE_ACTIVE, BRUSH_MENU_ACTIVE, COLOR_MENU_ACTIVE
    }

    private ActiveState activeState = ActiveState.DOODLE_ACTIVE;
    private boolean brushMenuMadeVisible = false, colorMenuMadeVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doodle);
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);

        LinearLayout brushMenuLinear = (LinearLayout) findViewById(R.id.brushMenuLinear);

        brushMenuLinear.animate().translationY(doodleView.getHeight());
        SeekBar seekAlpha = (SeekBar) findViewById(R.id.brushMenuAlphaSlider),
                seekWidth = (SeekBar) findViewById(R.id.brushMenuStrokeWidthSlider),
                seekRed = (SeekBar) findViewById(R.id.colorMenuRedSlider),
                seekGreen = (SeekBar) findViewById(R.id.colorMenuGreenSlider),
                seekBlue = (SeekBar) findViewById(R.id.colorMenuBlueSlider);
        seekAlpha.setOnSeekBarChangeListener(this);
        seekWidth.setOnSeekBarChangeListener(this);
        seekRed.setOnSeekBarChangeListener(this);
        seekGreen.setOnSeekBarChangeListener(this);
        seekBlue.setOnSeekBarChangeListener(this);

        ImageButton redoButton = (ImageButton) findViewById(R.id.redoButton);
        //redoButton.setScaleX(-1);
    }

    public void onColorButtonClicked(View v){
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        GridLayout buttonGrid = (GridLayout) findViewById(R.id.buttonGrid);
        Button colorButton = (Button) findViewById(R.id.buttonColor), brushButton = (Button) findViewById(R.id.buttonBrush),
                clearButton = (Button) findViewById(R.id.buttonClear);
        ImageButton undoButton = (ImageButton) findViewById(R.id.undoButton), redoButton = (ImageButton) findViewById(R.id.redoButton);
        LinearLayout colorMenuLinear = (LinearLayout) findViewById(R.id.colorMenuLinear);
        switch(activeState){
            case DOODLE_ACTIVE:
                undoButton.animate().translationY(-doodleView.getHeight());
                redoButton.animate().translationY(-doodleView.getHeight());
                doodleView.animate().translationY(-doodleView.getHeight());
                buttonGrid.animate().translationY(-doodleView.getHeight());
                brushButton.animate().translationX(doodleView.getWidth());
                clearButton.animate().translationX(doodleView.getWidth() * 2);
                colorButton.setText(R.string.done_label);
                colorMenuLinear.animate().translationY(0);
                if(!colorMenuMadeVisible){
                    colorMenuLinear.setTranslationY(doodleView.getHeight());
                    colorMenuLinear.setVisibility(View.VISIBLE);

                    colorMenuLinear.animate().translationY(0);
                    colorMenuMadeVisible = true;
                }
                activeState = ActiveState.COLOR_MENU_ACTIVE;
                break;
            case COLOR_MENU_ACTIVE:
                undoButton.animate().translationY(0);
                redoButton.animate().translationY(0);
                doodleView.animate().translationY(0);
                buttonGrid.animate().translationY(0);
                brushButton.animate().translationX(0);
                clearButton.animate().translationX(0);
                colorButton.setText(R.string.color_label);
                colorMenuLinear.animate().translationY(doodleView.getHeight());
                activeState = ActiveState.DOODLE_ACTIVE;
                break;
            default:
                break;
        }
    }

    public void onBrushButtonClicked(View v){
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        GridLayout buttonGrid = (GridLayout) findViewById(R.id.buttonGrid);
        Button colorButton = (Button) findViewById(R.id.buttonColor), brushButton = (Button) findViewById(R.id.buttonBrush),
                clearButton = (Button) findViewById(R.id.buttonClear);
        ImageButton undoButton = (ImageButton) findViewById(R.id.undoButton), redoButton = (ImageButton) findViewById(R.id.redoButton);
        LinearLayout brushMenuLinear = (LinearLayout) findViewById(R.id.brushMenuLinear);
        switch(activeState){
            case DOODLE_ACTIVE:
                undoButton.animate().translationY(-doodleView.getHeight());
                redoButton.animate().translationY(-doodleView.getHeight());
                doodleView.animate().translationY(-doodleView.getHeight());
                buttonGrid.animate().translationY(-doodleView.getHeight());
                colorButton.animate().translationX(-doodleView.getWidth());
                clearButton.animate().translationX(doodleView.getWidth());
                brushButton.setText(R.string.done_label);
                brushMenuLinear.animate().translationY(0);
                if(!brushMenuMadeVisible){
                    brushMenuLinear.setTranslationY(doodleView.getHeight());
                    brushMenuLinear.setVisibility(View.VISIBLE);

                    brushMenuLinear.animate().translationY(0);
                    brushMenuMadeVisible = true;
                }
                activeState = ActiveState.BRUSH_MENU_ACTIVE;
                break;
            case BRUSH_MENU_ACTIVE:
                undoButton.animate().translationY(0);
                redoButton.animate().translationY(0);
                doodleView.animate().translationY(0);
                buttonGrid.animate().translationY(0);
                colorButton.animate().translationX(0);
                clearButton.animate().translationX(0);
                brushButton.setText(R.string.brush_label);
                brushMenuLinear.animate().translationY(doodleView.getHeight());
                activeState = ActiveState.DOODLE_ACTIVE;
                break;
            default:
                break;
        }
    }

    public void onClearButtonClicked(View v) {
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        doodleView.clearPaint();
    }

    public void onUndoButtonClicked(View v){
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        doodleView.undoPath();
    }

    public void onRedoButtonClicked(View v){
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        doodleView.redoPath();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);

        switch (seekBar.getId()){
            case R.id.brushMenuAlphaSlider:
                doodleView.setStrokeAlpha(progress);
                doodleView.invalidate();
                break;
            case R.id.brushMenuStrokeWidthSlider:
                doodleView.setStrokeWidth(progress);
                doodleView.invalidate();
                break;
            case R.id.colorMenuRedSlider:
                doodleView.setStrokeRed(progress);
                break;
            case R.id.colorMenuGreenSlider:
                doodleView.setStrokeGreen(progress);
                break;
            case R.id.colorMenuBlueSlider:
                doodleView.setStrokeBlue(progress);
                break;
            default:
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){

    }
}
