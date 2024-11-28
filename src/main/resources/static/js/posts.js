// JavaScript function to handle post editing
function editPost(postId, title, description) {
    document.getElementById('editPostId').value = postId;
    document.getElementById('editTitle').value = title;
    document.getElementById('editDescription').value = description;
    $('#editFormModal').modal('show');
}
