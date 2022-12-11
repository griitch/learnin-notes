const $ = (g) => document.querySelector(g);

$("input[type=text]").setCustomValidity(
  "setCustomValidity method changes this "
);

const form = $("#novalForm");

const email = $("#mail");

const emailError = $("#mail + span.error");

email.addEventListener("input", () => {
  if (email.validity.valid) {
    emailError.textContent = "";
    emailError.className = "error";
  } else showError();
});

form.addEventListener("submit", (event) => {
  if (!email.validity.valid) {
    showError();
    event.preventDefault();
  }
});

function showError() {
  if (email.validity.valueMissing)
    emailError.textContent = "You need to enter an e-mail address.";

  else if (email.validity.typeMismatch)
    emailError.textContent = "Entered value needs to be an e-mail address.";
    
  else if (email.validity.tooShort) {
    emailError.textContent = `Email should be at least ${email.minLength} characters; you entered ${email.value.length}.`;
  }

  emailError.className = "error active";
}
