// JavaScript function to handle post editing
function editPost(postId, title, description) {
    // Validate input arguments
    if (!postId || !title || !description) {
        console.error("Invalid input for editPost. Ensure postId, title, and description are provided.");
        return;
    }

    // Set the values in the modal form fields
    const editPostIdField = document.getElementById('editPostId');
    const editTitleField = document.getElementById('editTitle');
    const editDescriptionField = document.getElementById('editDescription');

    if (editPostIdField) {
        editPostIdField.value = postId;
    } else {
        console.error("Element with ID 'editPostId' not found.");
    }

    if (editTitleField) {
        editTitleField.value = title;
    } else {
        console.error("Element with ID 'editTitle' not found.");
    }

    if (editDescriptionField) {
        editDescriptionField.value = description;
    } else {
        console.error("Element with ID 'editDescription' not found.");
    }

    // Show the modal
    const editFormModal = document.getElementById('editFormModal');
    if (editFormModal) {
        // Using Bootstrap's modal() method to display the modal
        const bootstrapModal = new bootstrap.Modal(editFormModal);
        bootstrapModal.show();
    } else {
        console.error("Element with ID 'editFormModal' not found.");
    }
}
