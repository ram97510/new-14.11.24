//PredictionController.java
package com.intense.mlapi;

import hex.genmodel.easy.RowData;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import org.springframework.beans.factory.annotation.Autowired;
import hex.genmodel.easy.prediction.RegressionModelPrediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping
    public ResponseEntity<Object> predict(@RequestBody RowData rowData) {
        try {
            RegressionModelPrediction prediction = predictionService.predict(rowData);
            return ResponseEntity.ok(prediction);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Prediction failed: " + e.getMessage());
        }
    }
}