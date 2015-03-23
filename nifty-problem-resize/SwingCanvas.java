import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
...

public class SwingCanvas extends SimpleApplication implements ScreenController {
       
    private Nifty nifty;
    ...
	
    @Override
    public void simpleInitApp() {
       
 //========================= NIFTY========================================           
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                                                  inputManager,
                                                  audioRenderer,
                                                  guiViewPort);
        nifty = niftyDisplay.getNifty();
        
        nifty.fromXml("Interface/test.xml", "hud", this);
        nifty.setDebugOptionPanelColors(true); // debug colors enabled       
        
        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay); 
//========================= NIFTY======================================== 

        inputManager.setCursorVisible(true);       
        initKeys();            
        initMyFlyByCam(); 
        setCamStart();
        initGrid();
        initScene();
        initInfoDisplay();
        initLight();
	
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    
    private void initScene(){ 
	   // some OpenGL stuff  
	}

    public JmeCanvasContext createJMEcanvas(){
        int frameRate=60;
        /** Canvas application settings */                 
        settings = new AppSettings(true);
        settings.setRenderer(AppSettings.LWJGL_OPENGL_ANY);
        settings.setFrameRate(frameRate); // Framerate  
        settings.setWidth(640);
        settings.setHeight(480);
        this.setSettings(settings);

        this.createCanvas();
        
        JmeCanvasContext ctx = (JmeCanvasContext) this.getContext();
        ctx.setSystemListener(this);
        Dimension dim = new Dimension(640, 480);              
        ctx.getCanvas().setPreferredSize(dim);
        return ctx;
    }    
     
public static void main(String[] args) {
     java.awt.EventQueue.invokeLater(new Runnable() {    
         public void run() {             
             JPanel canvasPanel = new JPanel (new BorderLayout()); //main panel
	     SwingCanvas canvasApplication = new SwingCanvas(); 
             JmeCanvasContext ctx = (JmeCanvasContext) canvasApplication.createJMEcanvas();            
             //canvasApplication.setPauseOnLostFocus(false); //nifty
             JFrame window = new JFrame("HuSki Editor");                    
             window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             optionDisplay optionPanel = new optionDisplay(); 
             // put all together           
             window.add(ctx.getCanvas()); //adding CanvasApp (canvasApplication) to Swing frame (window)
             canvasPanel.add(ctx.getCanvas(), BorderLayout.CENTER); // adding CanvasApp (canvasApplication) to BorderLayout position (JPanel)
             canvasPanel.add(optionPanel, BorderLayout.EAST); // adding optionPanel to BorderLayout position (JPanel)
             window.add(canvasPanel);
             window.pack();
             window.setLocation(640/2, 480/2); // start position
             window.setVisible(true);           
             canvasApplication.startCanvas(); 
         }     });
 }

    @Override
    public void simpleUpdate(float tpf){
        //write here update stuff
		...
    } 

}
