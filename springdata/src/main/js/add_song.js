import Joi from 'joi'
import { getCsrfInfo } from './modules/csrf.js'

const form = document.getElementById('addSongForm')
const songName = document.getElementById('songName')
const duration = document.getElementById('duration')
const genre = document.getElementById('genre')
const audio = document.getElementById('audio')
const submitButton = document.querySelector('#addSongForm > button')

const keysToInputs = new Map()
keysToInputs.set('songName', songName)
keysToInputs.set('duration', duration)
keysToInputs.set('genre', genre)
keysToInputs.set('audio', audio)

const songSchema = Joi.object({
    songName: Joi.string()
        .required(),
    duration: Joi.number()
        .required()
        .min(1),
    genre: Joi.string()
        .required(),
    audio: Joi.url()
        .required()
})

function trySubmitForm() {
    const songObject = {
        songName: songName.value,
        duration: duration.value,
        genre: genre.value,
        audio: audio.value
    }
    const validationResult = songSchema.validate(songObject, { abortEarly: false })
    console.log(validationResult)

    songName.setCustomValidity('')
    duration.setCustomValidity('')
    genre.setCustomValidity('')
    audio.setCustomValidity('')

    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            const inputField = keysToInputs.get(errorDetail.context.key)
            inputField.setCustomValidity(errorDetail.message)
            inputField.nextElementSibling.innerHTML = errorDetail.message
        }
    }

    form.classList.add('was-validated')

    if (!validationResult.error) {
        fetch('/api/songs', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                ...getCsrfInfo()
            },
            body: JSON.stringify(songObject)
        }).then((response) => {
            if (response.status === 201) {
                form.reset()
                form.classList.remove('was-validated')
            }
        })
    }
}

submitButton.addEventListener('click', trySubmitForm)
