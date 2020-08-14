package com.cuidedacidade.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cuidedacidade.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_issues.*

class IssuesFragment : Fragment() {

    private val viewModel: IssuesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_issues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO Corrigir change do titulo da activity
        //activity?.title = "Minhas solicitações"

        viewModel.getIssues()
            .observe(viewLifecycleOwner, Observer<List<Issue>> { issues ->

                //TODO FloatingActionButton deve aparecer nesse fragment
                activity?.fab?.show()

                list_issues.apply {
                    //TODO Jogar para fora do apply
                    setHasFixedSize(true)
                    adapter =
                        IssuesAdapter(issues)
                }
            })
    }
}