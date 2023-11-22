import tensorflow as tf
import numpy as np
from tensorflow import keras
import os


def build_model():
    # Create a model that receives 1 input value and returns 1 output value
    model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])

    # Define the algorithms for learning. You don't need to worry about this for now
    model.compile(optimizer='sgd', loss='mean_squared_error')
    return model


def train_model(model, xs, ys, epochs=500):
    # Train the model. Here we're saying the algorithm to try 500 random formulas to find the one that best matches
    # the input and output data.
    model.fit(xs, ys, epochs=epochs)


def predict_with_model(model, input_data):
    # Predict using the trained model
    return model.predict([input_data])


def save_model(model, export_path):
    # Save the model
    tf.keras.models.save_model(
        model,
        export_path,
        overwrite=True,
        include_optimizer=True,
        save_format=None,
        signatures=None,
        options=None
    )


def main():
    # Input data
    xs = np.array([-1.0, 0.0, 1.0, 2.0, 3.0, 4.0], dtype=float)
    ys = np.array([-2.0, 1.0, 4.0, 7.0, 10.0, 13.0], dtype=float)

    # Build the model
    model = build_model()

    # Train the model
    train_model(model, xs, ys)

    # Predict the value for x = 10. It will print a number very close to 31, like 30.9994 or something
    prediction = predict_with_model(model, 10.0)
    print(prediction)

    # Save the model
    model_dir = "./model"
    version = 1
    export_path = os.path.join(model_dir, str(version))
    print('export_path = {}\n'.format(export_path))
    save_model(model, export_path)
    print('\nSaved model: ' + export_path)


if __name__ == "__main__":
    main()
