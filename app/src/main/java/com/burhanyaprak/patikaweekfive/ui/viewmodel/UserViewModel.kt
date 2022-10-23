package com.burhanyaprak.patikaweekfive.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burhanyaprak.patikaweekfive.data.model.DataState
import com.burhanyaprak.patikaweekfive.data.model.UserDTO
import com.burhanyaprak.patikaweekfive.data.model.Users
import com.burhanyaprak.patikaweekfive.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _userLiveData = MutableLiveData<DataState<List<UserDTO>?>>()
    val postLiveData: LiveData<DataState<List<UserDTO>?>>
        get() = _userLiveData

    private val _eventStateLiveData = MutableLiveData<UserViewEvent>()
    val eventStateLiveData: LiveData<UserViewEvent>
        get() = _eventStateLiveData
    init {
        getUsers()
    }
    private fun getUsers() {
        _userLiveData.postValue(DataState.Loading())
        userRepository.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _userLiveData.postValue(DataState.Success(it.map { safePost ->
                            UserDTO(
                                id = safePost.id,
                                name = safePost.name,
                                email = safePost.email,
                                username = safePost.username,
                                website = safePost.website,
                            )
                        }))

                    } ?: kotlin.run {
                        _userLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _userLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _userLiveData.postValue(DataState.Error(t.message.toString()))
                _eventStateLiveData.postValue(UserViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }
    private fun isExists(postId: Int?): Boolean {
        postId?.let {
            userRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }
}

sealed class UserViewEvent {
    object NavigateToDetail : UserViewEvent()
    class ShowMessage(val message: String?) : UserViewEvent()
}