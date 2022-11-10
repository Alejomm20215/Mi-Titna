package com.example.mititna.helper

import android.annotation.SuppressLint
import android.content.Context
import com.example.mititna.data.model.db.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object ParseJsonDataFile {

    fun parse(context: Context, module: String): Triple<List<Exercise>, List<Lesson>, List<Question>> {
        val exercises = mutableListOf<Exercise>()
        val lessons = mutableListOf<Lesson>()
        val questions = mutableListOf<Question>()

        try {
            val jsonObject = JSONObject(loadJsonFromAssets(context, module) ?: "")
            if (jsonObject.has("exercises")) {
                val exerciseJsonArray = jsonObject.getJSONArray("exercises")
                for (i in 0 until exerciseJsonArray.length()) {
                    val exerciseJsonObject = exerciseJsonArray.getJSONObject(i)
                    val exercise = Exercise(
                        id = exerciseJsonObject.getString("id"),
                        exerciseNumber = exerciseJsonObject.getInt("exercise_number"),
                        title = exerciseJsonObject.getString("title"),
                        description = exerciseJsonObject.getString("description"),
                        isUnlocked = i == 0
                    )
                    parseLessonsFromExercise(exerciseJsonObject, exercise, lessons)
                    parseQuestionsFromExercise(exerciseJsonObject, exercise, questions)
                    exercises.add(exercise)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return Triple(
            exercises,
            lessons,
            questions
        )
    }

    @SuppressLint("DiscouragedApi")
    fun loadJsonFromAssets(context: Context, module: String): String? {
        val json: String? = try {
            val `is`: InputStream = context.resources.openRawResource(
                context.resources.getIdentifier(
                    "module_1",
                    "raw",
                    context.packageName
                )
            )
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun parseLessonsFromExercise(
        exerciseJsonObject: JSONObject,
        exercise: Exercise,
        lessons: MutableList<Lesson>
    ) {
        if (exerciseJsonObject.has("lessons")) {
            val lessonsJsonArray = exerciseJsonObject.getJSONArray("lessons")
            for (j in 0 until lessonsJsonArray.length()) {
                val lessonJsonObject = lessonsJsonArray.getJSONObject(j)
                val lesson = com.example.mititna.data.model.db.Lesson(
                    id = lessonJsonObject.getString("id"),
                    exerciseId = exercise.id,
                    lessonNumber = lessonJsonObject.getInt("lesson_number"),
                    phrase = lessonJsonObject.getString("phrase"),
                    phonetics = lessonJsonObject.getString("phonetics"),
                    translation = lessonJsonObject.getString("translation"),
                    description = lessonJsonObject.getString("description"),
                    examples = parseExampleFromLesson(lessonJsonObject.getJSONArray("examples"))
                )
                lessons.add(lesson)
            }
        }
    }

    private fun parseExampleFromLesson(exampleJsonArray: JSONArray): Examples {
        val examples = mutableListOf<Example>()
        for (i in 0 until exampleJsonArray.length()) {
            val exampleJsonObject = exampleJsonArray.getJSONObject(i)
            val example = Example(
                id = exampleJsonObject.getString("id"),
                sentence = exampleJsonObject.getString("sentence"),
                translation = exampleJsonObject.getString("translation")
            )
            examples.add(example)
        }

        return com.example.mititna.data.model.db.Examples(examples)
    }

    private fun parseQuestionsFromExercise(
        exerciseJsonObject: JSONObject,
        exercise: Exercise,
        questions: MutableList<Question>
    ) {
        if (exerciseJsonObject.has("quiz")) {
            val quizJsonArray = exerciseJsonObject.getJSONArray("quiz")
            for (k in 0 until quizJsonArray.length()) {
                val questionJsonObject = quizJsonArray.getJSONObject(k)
                val question = com.example.mititna.data.model.db.Question(
                    id = questionJsonObject.getString("id"),
                    exerciseId = exercise.id,
                    questionNumber = questionJsonObject.getInt("question_number"),
                    question = questionJsonObject.getString("question"),
                    questionType = questionJsonObject.getString("type"),
                    hint = questionJsonObject.getString("hint"),
                    correctAnswer = questionJsonObject.getString("correct_answer"),
                    options = parseOptionsFromQuestion(questionJsonObject.getJSONArray("options"))
                )
                questions.add(question)
            }
        }
    }

    private fun parseOptionsFromQuestion(optionsJsonArray: JSONArray): Options {
        val options = mutableListOf<String>()
        for (i in 0 until optionsJsonArray.length()) {
            options.add(optionsJsonArray.getString(i))
        }
        return Options(options)
    }
}