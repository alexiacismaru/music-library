import Joi from 'joi'
import { getCsrfInfo } from './modules/csrf.js'

const form = document.getElementById('addAlbumForm')
const title = document.getElementById('title')
const releaseDate = document.getElementById('releaseDate')
const cover = document.getElementById('cover')
const submitButton = document.querySelector('#addAlbumForm > button')

const keysToInputs = new Map()
keysToInputs.set('title', title)
keysToInputs.set('releaseDate', releaseDate)
keysToInputs.set('cover', cover)

const albumSchema = Joi.object({
    title: Joi.string()
        .required(),
    releaseDate: Joi.date()
        .required(),
    cover: Joi.url()
        .required()
})

function trySubmitForm() {
    const albumObject = {
        title: title.value,
        releaseDate: releaseDate.value,
        cover: cover.value
    }
    const validationResult = albumSchema.validate(albumObject, { abortEarly: false })
    console.log(validationResult)

    title.setCustomValidity('')
    releaseDate.setCustomValidity('')
    cover.setCustomValidity('')

    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            const inputField = keysToInputs.get(errorDetail.context.key)
            inputField.setCustomValidity(errorDetail.message)
            inputField.nextElementSibling.innerHTML = errorDetail.message
        }
    }

    form.classList.add('was-validated')

    if (!validationResult.error) {
        fetch('/api/albums', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                ...getCsrfInfo()
            },
            body: JSON.stringify(albumObject)
        })
            .then((response) => {
                if (response.status === 201) {
                    form.reset()
                    form.classList.remove('was-validated')
                }
            })
    }
}

submitButton.addEventListener('click', trySubmitForm)
