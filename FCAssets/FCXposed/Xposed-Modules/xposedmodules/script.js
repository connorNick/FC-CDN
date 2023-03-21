function createAppHtml(app) {
    return `
    <div class="app-card">
        <img src="${app.icon}">
        <div>
            <h2><a href="${app.githubRepo}">${app.name}</a></h2>
            <p>バージョン: ${app.version}</p>
            <p>${app.description}</p>
        </div>
    </div>`
}

fetch("apps.json")
    .then(response => response.json())
    .then(data => {
        let appContainer = document.getElementById("app-container")
        data.apps.forEach(app => {
            appContainer.innerHTML += createAppHtml(app)
        });
    })
    .catch(error => console.log(error));