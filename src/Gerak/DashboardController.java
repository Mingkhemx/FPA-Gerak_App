package Gerak;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    
    // FXML Injected Elements - Navigation
    @FXML private Button dashboardBtn;
    @FXML private Button challengeBtn;
    @FXML private Button monitoringBtn;
    @FXML private Button historyBtn;
    @FXML private Button settingsBtn;
    
    // FXML Injected Elements - Welcome Section
    @FXML private Label welcomeTitle;
    @FXML private Button startWorkoutBtn;
    @FXML private Button quickMeditationBtn;
    @FXML private Button logFoodBtn;
    
    // FXML Injected Elements - Stats Cards
    @FXML private VBox stepsCard;
    @FXML private VBox caloriesCard;
    @FXML private VBox waterCard;
    @FXML private VBox sleepCard;
    @FXML private Label stepsNumber;
    @FXML private Label caloriesNumber;
    @FXML private Label waterNumber;
    @FXML private Label sleepNumber;
    @FXML private ProgressBar stepsProgress;
    @FXML private ProgressBar caloriesProgress;
    @FXML private ProgressBar waterProgress;
    @FXML private ProgressBar sleepProgress;
    
    // FXML Injected Elements - Main Dashboard
    @FXML private VBox workoutCard;
    @FXML private VBox nutritionCard;
    @FXML private VBox heartRateCard;
    @FXML private VBox progressCard;
    @FXML private Button startWorkoutCardBtn;
    @FXML private Button logMealBtn;
    @FXML private Label heartRateNumber;
    @FXML private ProgressBar proteinProgress;
    @FXML private ProgressBar carbsProgress;
    @FXML private ProgressBar fatsProgress;
    @FXML private ProgressBar weekProgress;
    
    // FXML Injected Elements - Quick Actions
    @FXML private VBox meditationCard;
    @FXML private VBox timerCard;
    @FXML private VBox recipeCard;
    @FXML private VBox challengeCard;
    @FXML private Button meditationBtn;
    @FXML private Button timerBtn;
    @FXML private Button recipeBtn;
    @FXML private Button challengeActionBtn;
    
    // FXML Injected Elements - Bottom Bar
    @FXML private Button syncBtn;
    @FXML private Label syncText;
    
    // Animation and Effect Variables
    private Timeline heartRateAnimation;
    private Timeline updateStatsAnimation;
    private Random random = new Random();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventHandlers();
        setup3DEffects();
        setupAnimations();
        updateWelcomeMessage();
        startRealTimeUpdates();
    }
    
    /**
     * Setup event handlers for all interactive elements
     */
    private void setupEventHandlers() {
        // Navigation buttons
        dashboardBtn.setOnAction(e -> handleNavigation("Dashboard"));
        challengeBtn.setOnAction(e -> handleNavigation("Challenge"));
        monitoringBtn.setOnAction(e -> handleNavigation("Monitoring"));
        historyBtn.setOnAction(e -> handleNavigation("History"));
        settingsBtn.setOnAction(e -> handleNavigation("Settings"));
        
        // Main action buttons
        startWorkoutBtn.setOnAction(e -> handleStartWorkout());
        quickMeditationBtn.setOnAction(e -> handleQuickMeditation());
        logFoodBtn.setOnAction(e -> handleLogFood());
        startWorkoutCardBtn.setOnAction(e -> handleStartWorkout());
        logMealBtn.setOnAction(e -> handleLogMeal());
        
        // Quick action buttons
        meditationBtn.setOnAction(e -> handleMeditation());
        timerBtn.setOnAction(e -> handleTimer());
        recipeBtn.setOnAction(e -> handleRecipes());
        challengeActionBtn.setOnAction(e -> handleDailyChallenge());
        
        // Bottom bar
        syncBtn.setOnAction(e -> handleSync());
    }
    
    /**
     * Setup 3D effects for buttons and cards
     */
    private void setup3DEffects() {
        // Setup 3D effects for all buttons
        setupButton3DEffect(startWorkoutBtn);
        setupButton3DEffect(quickMeditationBtn);
        setupButton3DEffect(logFoodBtn);
        setupButton3DEffect(startWorkoutCardBtn);
        setupButton3DEffect(logMealBtn);
        setupButton3DEffect(meditationBtn);
        setupButton3DEffect(timerBtn);
        setupButton3DEffect(recipeBtn);
        setupButton3DEffect(challengeActionBtn);
        setupButton3DEffect(syncBtn);
        
        // Setup 3D effects for navigation buttons
        setupButton3DEffect(dashboardBtn);
        setupButton3DEffect(challengeBtn);
        setupButton3DEffect(monitoringBtn);
        setupButton3DEffect(historyBtn);
        setupButton3DEffect(settingsBtn);
        
        // Setup 3D effects for cards
        setupCard3DEffect(stepsCard);
        setupCard3DEffect(caloriesCard);
        setupCard3DEffect(waterCard);
        setupCard3DEffect(sleepCard);
        setupCard3DEffect(workoutCard);
        setupCard3DEffect(nutritionCard);
        setupCard3DEffect(heartRateCard);
        setupCard3DEffect(progressCard);
        setupCard3DEffect(meditationCard);
        setupCard3DEffect(timerCard);
        setupCard3DEffect(recipeCard);
        setupCard3DEffect(challengeCard);
    }
    
    /**
     * Setup 3D effect for individual button
     */
    private void setupButton3DEffect(Button button) {
        if (button == null) return;
        
        // Create drop shadow for 3D effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.3));
        
        // Create inner shadow for pressed effect
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(5.0);
        innerShadow.setOffsetX(2.0);
        innerShadow.setOffsetY(2.0);
        innerShadow.setColor(Color.color(0, 0, 0, 0.5));
        
        button.setEffect(dropShadow);
        
        // Mouse enter effect - raise button
        button.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), button);
            scaleUp.setToX(1.05);
            scaleUp.setToY(1.05);
            
            TranslateTransition moveUp = new TranslateTransition(Duration.millis(100), button);
            moveUp.setToY(-2);
            
            // Enhanced shadow
            DropShadow enhancedShadow = new DropShadow();
            enhancedShadow.setRadius(12.0);
            enhancedShadow.setOffsetX(4.0);
            enhancedShadow.setOffsetY(4.0);
            enhancedShadow.setColor(Color.color(0, 0, 0, 0.4));
            button.setEffect(enhancedShadow);
            
            ParallelTransition parallel = new ParallelTransition(scaleUp, moveUp);
            parallel.play();
        });
        
        // Mouse exit effect - lower button
        button.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), button);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            
            TranslateTransition moveDown = new TranslateTransition(Duration.millis(100), button);
            moveDown.setToY(0);
            
            button.setEffect(dropShadow);
            
            ParallelTransition parallel = new ParallelTransition(scaleDown, moveDown);
            parallel.play();
        });
        
        // Mouse pressed effect - press button down
        button.setOnMousePressed(e -> {
            ScaleTransition scalePress = new ScaleTransition(Duration.millis(50), button);
            scalePress.setToX(0.98);
            scalePress.setToY(0.98);
            
            TranslateTransition pressDown = new TranslateTransition(Duration.millis(50), button);
            pressDown.setToY(1);
            
            button.setEffect(innerShadow);
            
            ParallelTransition parallel = new ParallelTransition(scalePress, pressDown);
            parallel.play();
        });
        
        // Mouse released effect - return to hover state
        button.setOnMouseReleased(e -> {
            ScaleTransition scaleRelease = new ScaleTransition(Duration.millis(50), button);
            scaleRelease.setToX(1.05);
            scaleRelease.setToY(1.05);
            
            TranslateTransition releaseUp = new TranslateTransition(Duration.millis(50), button);
            releaseUp.setToY(-2);
            
            DropShadow enhancedShadow = new DropShadow();
            enhancedShadow.setRadius(12.0);
            enhancedShadow.setOffsetX(4.0);
            enhancedShadow.setOffsetY(4.0);
            enhancedShadow.setColor(Color.color(0, 0, 0, 0.4));
            button.setEffect(enhancedShadow);
            
            ParallelTransition parallel = new ParallelTransition(scaleRelease, releaseUp);
            parallel.play();
        });
    }
    
    /**
     * Setup 3D effect for cards
     */
    private void setupCard3DEffect(VBox card) {
        if (card == null) return;
        
        // Create subtle drop shadow for cards
        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(6.0);
        cardShadow.setOffsetX(2.0);
        cardShadow.setOffsetY(2.0);
        cardShadow.setColor(Color.color(0, 0, 0, 0.2));
        
        card.setEffect(cardShadow);
        
        // Mouse enter effect - subtle lift
        card.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), card);
            scaleUp.setToX(1.02);
            scaleUp.setToY(1.02);
            
            TranslateTransition moveUp = new TranslateTransition(Duration.millis(150), card);
            moveUp.setToY(-3);
            
            // Enhanced shadow for hover
            DropShadow hoverShadow = new DropShadow();
            hoverShadow.setRadius(10.0);
            hoverShadow.setOffsetX(3.0);
            hoverShadow.setOffsetY(5.0);
            hoverShadow.setColor(Color.color(0, 0, 0, 0.3));
            card.setEffect(hoverShadow);
            
            ParallelTransition parallel = new ParallelTransition(scaleUp, moveUp);
            parallel.play();
        });
        
        // Mouse exit effect - return to normal
        card.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), card);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            
            TranslateTransition moveDown = new TranslateTransition(Duration.millis(150), card);
            moveDown.setToY(0);
            
            card.setEffect(cardShadow);
            
            ParallelTransition parallel = new ParallelTransition(scaleDown, moveDown);
            parallel.play();
        });
    }
    
    /**
     * Setup various animations
     */
    private void setupAnimations() {
        // Heart rate animation
        if (heartRateNumber != null) {
            heartRateAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateHeartRate())
            );
            heartRateAnimation.setCycleCount(Timeline.INDEFINITE);
            heartRateAnimation.play();
        }
        
        // Stats update animation
        updateStatsAnimation = new Timeline(
            new KeyFrame(Duration.seconds(30), e -> updateStats())
        );
        updateStatsAnimation.setCycleCount(Timeline.INDEFINITE);
        updateStatsAnimation.play();
    }
    
    /**
     * Update welcome message based on time of day
     */
    private void updateWelcomeMessage() {
        if (welcomeTitle == null) return;
        
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String greeting;
        
        if (hour < 12) {
            greeting = "Good Morning, John!";
        } else if (hour < 17) {
            greeting = "Good Afternoon, John!";
        } else {
            greeting = "Good Evening, John!";
        }
        
        welcomeTitle.setText(greeting);
    }
    
    /**
     * Update heart rate with realistic variation
     */
    private void updateHeartRate() {
        if (heartRateNumber == null) return;
        
        int currentRate = Integer.parseInt(heartRateNumber.getText());
        int variation = random.nextInt(5) - 2; // -2 to +2
        int newRate = Math.max(60, Math.min(80, currentRate + variation));
        
        // Animate number change
        Timeline timeline = new Timeline();
        final int finalRate = newRate;
        
        for (int i = 0; i <= 10; i++) {
            final int step = i;
            KeyFrame keyFrame = new KeyFrame(
                Duration.millis(i * 100),
                e -> {
                    int interpolatedRate = currentRate + (finalRate - currentRate) * step / 10;
                    heartRateNumber.setText(String.valueOf(interpolatedRate));
                }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        
        timeline.play();
    }
    
    /**
     * Update various stats with small increments
     */
    private void updateStats() {
        // Update steps
        if (stepsNumber != null && stepsProgress != null) {
            String currentSteps = stepsNumber.getText().replace(",", "");
            int steps = Integer.parseInt(currentSteps);
            steps += random.nextInt(50) + 10; // Add 10-59 steps
            stepsNumber.setText(String.format("%,d", steps));
            stepsProgress.setProgress(Math.min(1.0, steps / 16667.0)); // Goal: 16667 steps
        }
        
        // Update calories
        if (caloriesNumber != null && caloriesProgress != null) {
            String currentCals = caloriesNumber.getText().replace(",", "");
            int calories = Integer.parseInt(currentCals);
            calories += random.nextInt(30) + 5; // Add 5-34 calories
            caloriesNumber.setText(String.format("%,d", calories));
            caloriesProgress.setProgress(Math.min(1.0, calories / 2500.0)); // Goal: 2500 calories
        }
    }
    
    /**
     * Start real-time updates
     */
    private void startRealTimeUpdates() {
        Timeline realTimeUpdate = new Timeline(
            new KeyFrame(Duration.minutes(1), e -> {
                updateSyncTime();
                updateWelcomeMessage();
            })
        );
        realTimeUpdate.setCycleCount(Timeline.INDEFINITE);
        realTimeUpdate.play();
    }
    
    /**
     * Update sync time
     */
    private void updateSyncTime() {
        if (syncText == null) return;
        
        String[] timeUnits = {"Just now", "1 min ago", "2 min ago", "3 min ago"};
        String randomTime = timeUnits[random.nextInt(timeUnits.length)];
        syncText.setText("Last sync: " + randomTime);
    }
    
    // Event Handler Methods
    private void handleNavigation(String page) {
        System.out.println("Navigating to: " + page);
        // Implement navigation logic here
    }
    
    private void handleStartWorkout() {
        System.out.println("Starting workout...");
        // Implement workout start logic
        showNotification("Workout started! Let's crush those goals!");
    }
    
    private void handleQuickMeditation() {
        System.out.println("Starting quick meditation...");
        showNotification("Take a deep breath and relax...");
    }
    
    private void handleLogFood() {
        System.out.println("Opening food logger...");
        showNotification("What did you eat today?");
    }
    
    private void handleLogMeal() {
        System.out.println("Logging meal...");
        showNotification("Meal logged successfully!");
    }
    
    private void handleMeditation() {
        System.out.println("Starting 5-minute meditation...");
        showNotification("Starting your 5-minute mindfulness session...");
    }
    
    private void handleTimer() {
        System.out.println("Starting workout timer...");
        showNotification("HIIT timer ready! Let's go!");
    }
    
    private void handleRecipes() {
        System.out.println("Opening healthy recipes...");
        showNotification("Discovering new healthy recipes...");
    }
    
    private void handleDailyChallenge() {
        System.out.println("Accepting daily challenge...");
        showNotification("Challenge accepted! Time for 50 squats!");
    }
    
    private void handleSync() {
        System.out.println("Syncing with fitness tracker...");
        
        // Visual feedback for sync
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), syncBtn);
        rotate.setByAngle(360);
        rotate.play();
        
        // Simulate sync delay
        Timeline syncDelay = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                syncText.setText("Last sync: Just now");
                showNotification("Sync completed successfully!");
            })
        );
        syncDelay.play();
    }
    
    /**
     * Show notification to user
     */
    private void showNotification(String message) {
        System.out.println("Notification: " + message);
        // Here you could implement a toast notification or status update
        // For now, we'll just print to console
    }
    
    /**
     * Cleanup method for animations
     */
    public void cleanup() {
        if (heartRateAnimation != null) {
            heartRateAnimation.stop();
        }
        if (updateStatsAnimation != null) {
            updateStatsAnimation.stop();
        }
    }
}