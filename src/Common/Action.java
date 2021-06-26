package Common;

import java.io.Serializable;

public enum Action implements Serializable {
    login,
    logout,
    signup,
    addPost,
    getPosts,
    getMyPost,
    getProfiles,
    setProfile,
    getProfile,
    follow,
    unfollow,
    setInformation,
    getInformation,
    like,
    dislike,
    repost,
    unReposted,
    setComment,
    getComment,
    //extra
    changeProfile,
    getFollowers,
    getFollowings,
    getNumbers,
    getProfilesNumber,
    getPostDetails,
    timelineUpdate,
    getLikes,
    getReposts,
}
