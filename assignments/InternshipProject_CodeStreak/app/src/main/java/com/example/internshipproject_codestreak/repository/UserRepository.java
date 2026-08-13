package com.example.internshipproject_codestreak.repository;

import com.example.internshipproject_codestreak.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public Task<Void> updateUser(String uid, User user) {
        return db.collection("users")
                .document(uid)
                .set(user);
    }

    public Task<User> resetHeartsIfNeeded(String uid) {

        return getUser(uid)
                .continueWithTask(task -> {

                    User user = task.getResult();

                    if (user == null) {
                        return com.google.android.gms.tasks.Tasks.forResult(null);
                    }

                    String today =
                            new SimpleDateFormat(
                                    "yyyy-MM-dd",
                                    Locale.getDefault()
                            ).format(new Date());

                    String lastReset =
                            user.getLastHeartReset();

                    // First time checking hearts
                    if (lastReset == null
                            || !lastReset.equals(today)) {

                        user.setHearts(5);
                        user.setLastHeartReset(today);

                        return updateUser(uid, user)
                                .continueWith(
                                        updateTask -> user
                                );
                    }

                    return com.google.android.gms.tasks.Tasks.forResult(user);
                });
    }


}
