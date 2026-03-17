from flask import Flask, request, jsonify
from transformers import pipeline

app = Flask(__name__)

sentiment_model = pipeline("sentiment-analysis")

@app.route("/predict", methods=["POST"])
def predict():

    text = request.json["text"]

    result = sentiment_model(text)[0]

    sentiment = result["label"].lower()

    return jsonify({
        "sentiment": sentiment
    })


app.run(host="0.0.0.0", port=5000)
