import Joi from 'joi'
import { getCsrfInfo } from './modules/csrf.js'

const form = document.getElementById('addArtistForm')
const name = document.getElementById('name')
const profile = document.getElementById('profile')
const description = document.getElementById('description')
const submitButton = document.querySelector('#addArtistForm > button')

const keysToInputs = new Map()
keysToInputs.set('name', name)
keysToInputs.set('profile', profile)
keysToInputs.set('description', description)

const artistSchema = Joi.object({
    name: Joi.string()
        .required(),
    gender: Joi.url()
        .required(),
    debutYear: Joi.number()
        .required()
        .min(1)
})

function trySubmitForm() {
    const artistObject = {
        name: name.value,
        profile: profile.value,
        description: description.value
    }
    const validationResult = artistSchema.validate(artistObject, { abortEarly: false })
    console.log(validationResult)

    name.setCustomValidity('')
    profile.setCustomValidity('')
    description.setCustomValidity('')

    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            const inputField = keysToInputs.get(errorDetail.context.key)
            inputField.setCustomValidity(errorDetail.message)
            inputField.nextElementSibling.innerHTML = errorDetail.message
        }
    }

    form.classList.add('was-validated')

    if (!validationResult.error) {
        fetch('/api/artists', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                ...getCsrfInfo()
            },
            body: JSON.stringify(artistObject)
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
