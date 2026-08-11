package com.example.internshipproject_codestreak.repository;

import com.example.internshipproject_codestreak.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepository {

    private final FirebaseFirestore db;

    public UserRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public Task<Void> createUser(String uid, User user) {
        return db.collection("users")
                .document(uid)
                .set(user);
    }

    public Task<User> getUser(String uid) {
        return db.collection("users")
                .document(uid)
                .get()
                .continueWith(task -> task.getResult().toObject(User.class));
    }

}
