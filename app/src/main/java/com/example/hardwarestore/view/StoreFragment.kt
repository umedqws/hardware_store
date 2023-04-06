package com.example.hardwarestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentStoreBinding
import com.example.hardwarestore.viewmodel.CategoryViewModel
import com.example.hardwarestore.viewmodel.ProductsViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import java.util.*

class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchAdapter:SearchAdapter = SearchAdapter()
        val adapter: HitAdapter = HitAdapter()
        val categoryAdapter: CategoryAdapter = CategoryAdapter()
        val productAdapter: ProdtuctAdapter = ProdtuctAdapter()



        binding.search.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                binding.layout.visibility = GONE
                binding.searchRv.visibility = VISIBLE
            } else {
                binding.layout.visibility = VISIBLE
                binding.searchRv.visibility = GONE
            }
        }


        binding.hitRcView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.hitRcView.adapter = adapter
        binding.product.layoutManager =
            GridLayoutManager(binding.root.context,2)
        binding.product.adapter = productAdapter
        binding.category.layoutManager =
            LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
        binding.category.adapter = categoryAdapter


        val viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        val cViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]




        viewModel.list().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            productAdapter.submitList(it)
        }
       cViewModel.listCategory().observe(viewLifecycleOwner){
            categoryAdapter.submitList(it)
        }



        binding.textHit.setOnClickListener {
            viewModel.insertProduct("Бензопила STIHL MS 180 14", "Топливо : Бензин АИ-92 c маслом для двухтактных двигателей\n" +
                    "\n" +
                    "Тип пилы : Бензиновая\n" +
                    "\n" +
                    "Длина шины, дюйм : 14\"\n" +
                    "\n" +
                    "Шаг цепи, дюйм : 3/8’’\n" +
                    "\n" +
                    "Ширина паза, мм : 1.3", 2500,"Бензопила STIHL MS 180 (11302000108) — лёгкая, надёжная, сбалансированная модель мощностью 1,5 кВт, рабочим объемом двигателя 31,8 см³ и весом 3,9 кг для бытового применения. Благодаря своим характеристикам она очень проста в обращении даже для начинающих пользователей. Подходит для периодического использования на участке, небольшого строительства. Станет незаменимой для заготовки дров, обрезки деревьев, распила досок и бревен.\n" +
                    "Цепной тормоз с механизмом QuickStop для моментальной остановки повышает безопасность оператора, а антивибрационная система снижает отдачу мотора на рукоятку.  \n" +
                    "\n",7,R.drawable.b)
            viewModel.insertProduct("Бензопила STIHL MS 230 14", "Характеристики\n" +
                    "\n" +
                    "Топливо : Бензин АИ-92 c маслом для двухтактных двигателей\n" +
                    "\n" +
                    "Тип пилы : Бензиновая\n" +
                    "\n" +
                    "Длина шины, дюйм : 14\"\n" +
                    "\n" +
                    "Шаг цепи, дюйм : 3/8’’\n" +
                    "\n" +
                    "Ширина паза, мм : 1.3", 1500,"Прочная и мощная бензопила STIHL MS 230 14\" (11232000367) с отличным удельным весом, антивибрационная система STIHL, открываемые без помощи инструмента крышки баков. Идеально подходит для заготовки дров и строительства с использованием древесины. Хорошо подходит для валки небольших деревьев.",7,R.drawable.b)

            viewModel.insertProduct("Газонокосилка электрическая STIHL RМЕ 235", "Характеристики\n" +
                    "\n" +
                    "Самоходность : Нет\n" +
                    "\n" +
                    "Мульчирование : Нет\n" +
                    "\n" +
                    "Выброс травы : Травосборник/выброс назад\n" +
                    "\n" +
                    "ТИП газонокосилки : Электрические", 2500,"STIHL RМЕ-235 (63110112410) легкая электрическая газонокосилка для небольших газонов\n" +
                    "\n" +
                    "\n" +
                    "Если вы хотите стричь газоны небольшого размера, то тихая электрическая газонокосилка STIHL RME 235 - это правильный выбор.\n" +
                    "Благодаря простой, но прочной конструкции уход за газоном особенно удобен.\n" +
                    "Оптимальную высоту стрижки травы можно регулировать с помощью центральной регулировки высоты стрижки в пять шагов. Это гарантирует убедительные результаты кошения и чистую стрижку в вашем саду.\n" +
                    "Встроенный индикатор уровня заполнения травосборника.",2,R.drawable.gazon)

            viewModel.insertProduct("Газонокосилка электрическая STIHL RМЕ 545 V", "Самоходность : Да\n" +
                    "\n" +
                    "Мульчирование : Опция\n" +
                    "\n" +
                    "Выброс травы : Травосборник/выброс назад\n" +
                    "\n" +
                    "ТИП газонокосилки : Электрические", 1500,"STIHL RМЕ-545V (63400112425) удобная электрическая газонокосилка с приводом Vario\n" +
                    "\n" +
                    "\n" +
                    "С помощью привода скорость можно плавно регулировать с помощью рычага управления на рукоятке.\n" +
                    "Электрическая газонокосилка STIHL RME 545 C удобна и мощна в работе.\n" +
                    "С шириной реза 43 см, она идеально подходит для садов среднего размера.\n" +
                    "После использования STIHL RME 545 можно легко транспортировать благодаря складному травосборнику и хранить экономя место.\n" +
                    "Если вы хотите мульчировать газон, STIHL RME 545 можно также превратить в мульчирующую газонокосилку с соответствующим комплектом для мульчирования в качестве аксессуара.",2,R.drawable.gazon)


            viewModel.insertProduct("Садовый трактор STIHL RT 4097 SX", "Характеристики\n" +
                    "\n" +
                    "Привод : Задний\n" +
                    "\n" +
                    "Топливо : Бензин АИ-92\n" +
                    "\n" +
                    "Мульчирование : Опция\n" +
                    "\n" +
                    "Выброс травы : Боковой ", 2500,"Садовый минитрактор Stihl RT 4097 SX с двухлопастной системой скашивания и мощностью 12 л.с. И рабочей шириной скашивания 95 см.\n" +
                    "\n" +
                    "Гидростатическая система привода обеспечивает плавность хода.\n" +
                    "Садовый трактор Stihl RT 4097SX оснащен центральной системой регулировки высоты стрижки.\n" +
                    "Большие колеса с профильными шинами не повреждают газон.\n" +
                    "Прочная рама обеспечивает надежность всей конструкции.\n" +
                    "Все органы управления трактором находятся под рукой, что обеспечивает комфорт при работе.",3,R.drawable.traktor)
              viewModel.insertProduct("Садовый трактор STIHL RT 4097 SX", "Характеристики\n" +
                    "\n" +
                    "Привод : Задний\n" +
                    "\n" +
                    "Топливо : Бензин АИ-92\n" +
                    "\n" +
                    "Мульчирование : Опция\n" +
                    "\n" +
                    "Выброс травы : Боковой ", 2500,"Садовый минитрактор Stihl RT 4097 SX с двухлопастной системой скашивания и мощностью 12 л.с. И рабочей шириной скашивания 95 см.\n" +
                    "\n" +
                    "Гидростатическая система привода обеспечивает плавность хода.\n" +
                    "Садовый трактор Stihl RT 4097SX оснащен центральной системой регулировки высоты стрижки.\n" +
                    "Большие колеса с профильными шинами не повреждают газон.\n" +
                    "Прочная рама обеспечивает надежность всей конструкции.\n" +
                    "Все органы управления трактором находятся под рукой, что обеспечивает комфорт при работе.",3,R.drawable.traktor)

            viewModel.insertProduct("БЕНЗОБУР STIHL BT 131", "Силовым агрегатом бензобура BT 131 выступает 2-тактный двигатель STIHL с технологией 4-MIX мощностью 1.9 л.с. ", 1500,"Мотобур STIHL BT 131 представляет собой высокопроизводительный и легкий инструмент для профессионалов, который предназначен для выполнения следующих работ: бурение лунок в почве и во льду, установка заборов, ландшафтные и строительные работы. К основным особенностям бензинового бура можно отнести высокую мощность, экономичный двигатель и увеличенный объем топливного бака, в сравнении в предшевствующей моделью, что позволяет работать дольше без остановки на дозапраку.",4,R.drawable.benzobur)
            viewModel.insertProduct("БЕНЗОБУР STIHL BT 131", "Силовым агрегатом бензобура BT 131 выступает 2-тактный двигатель STIHL с технологией 4-MIX мощностью 1.9 л.с. ", 1500,"Мотобур STIHL BT 131 представляет собой высокопроизводительный и легкий инструмент для профессионалов, который предназначен для выполнения следующих работ: бурение лунок в почве и во льду, установка заборов, ландшафтные и строительные работы. К основным особенностям бензинового бура можно отнести высокую мощность, экономичный двигатель и увеличенный объем топливного бака, в сравнении в предшевствующей моделью, что позволяет работать дольше без остановки на дозапраку.",4,R.drawable.benzobur)

            viewModel.insertProduct("Триммер (мотокоса) STIHL FS 55", "Характеристики"+
                    "Режущий элемент : Нож+Леска" +
                    "Тип ручки :Рога"+
                    "Тип триммера : Мотокоса"+
                    "Тип двигателя : Бензиновый ", 2500,"Мотокоса STIHL FS 55 (41402000475) любительского класса мощностью 0,75 кВт и рабочим объемом двигателя 27,2 см³. Оснащена двухручной рукояткой, благодаря которой легко маневрировать и управлять инструментом: все элементы вынесены на нее, а регулировка положения осуществляется с помощью одного рычага. \n" +
                    "\n" +
                    "Подходит для стрижки газонов, поросли на дачном участке. Мотокоса Stihl FS 55 использует триммерную головку AutoCut C 25-2. С оснасткой режущим инструментом эффективна для свалявшейся травы, камыша, крапивы. \n" +
                    "\n" +
                    "Купить мотокосу STIHL FS 55 в СПб можно у нас. В комплектацию входит само устройство, нож, ключ, подвес, защитные очки и документация (паспорт и гарантийный талон). ",5,R.drawable.motokos)
            viewModel.insertProduct("Триммер (мотокоса) STIHL FS 55", "Характеристики"+
                    "Режущий элемент : Нож+Леска" +
                    "Тип ручки :Рога"+
                    "Тип триммера : Мотокоса"+
                    "Тип двигателя : Бензиновый ", 2500,"Мотокоса STIHL FS 55 (41402000475) любительского класса мощностью 0,75 кВт и рабочим объемом двигателя 27,2 см³. Оснащена двухручной рукояткой, благодаря которой легко маневрировать и управлять инструментом: все элементы вынесены на нее, а регулировка положения осуществляется с помощью одного рычага. \n" +
                    "\n" +
                    "Подходит для стрижки газонов, поросли на дачном участке. Мотокоса Stihl FS 55 использует триммерную головку AutoCut C 25-2. С оснасткой режущим инструментом эффективна для свалявшейся травы, камыша, крапивы. \n" +
                    "\n" +
                    "Купить мотокосу STIHL FS 55 в СПб можно у нас. В комплектацию входит само устройство, нож, ключ, подвес, защитные очки и документация (паспорт и гарантийный талон). ",5,R.drawable.motokos)


            viewModel.insertProduct("Мойка высокого давления STIHL RE 90", "Характеристики" +
                    "Подогрев воды : нет"+
                    "Функция самовсасывания : есть" +
                    "Материал помпы : алюмини" +
                    "Мощность, кВт : 1.8 ", 1500,"Легкая и компактная мойка высокого давления (100 Бар) STIHL RE-90 (49510124508) в базовой комплектации.\n" +
                    "\n" +
                    "      Преимущества:\n" +
                    "Алюминиевая головка насоса\n" +
                    "Встроенная тележка\n" +
                    "Распылитель моющего средства с пульверизатором\n" +
                    "Роторная форсунка и регулируемая плоскоструйная форсунка\n" +
                    "Быстроразъемная муфта на пистолете-распылителе, предотвращающая перекручивание шланга\n" +
                    "Парковочное положение для пистолета при перерывах в работе\n" +
                    "Напорный шланг длинной 6м с текстильным армированием\n" +
                    "Ручка для транспортировки.",6, R.drawable.moyka)
            viewModel.insertProduct("Пеногенератор STIHL для RE-230-462", "Совместимость : RE-230-462" +
                    "Объем, л : 1 ", 2500,"Каталог \n" +
                    " Мойки и очистительные устройства \n" +
                    " Мойки высокого давления и принадлежности \n" +
                    " Пенные насадки\n" +
                    "Пеногенератор STIHL для RE-230-462 (49255009600)\n" +
                    "\n" +
                    "Артикул товара: 49255009600\n" +
                    "Сертификат дилера\n" +
                    "Нашли дешевле?\n" +
                    "2 990 ₽\n" +
                    "\n" +
                    "Доставка\n" +
                    "Транспортной компанией\n" +
                    "Наличие\n" +
                    "Центральный склад\n" +
                    "В наличии: 1 шт.\n" +
                    "Пеногенератор STIHL для RE-230-462 (49255009600)\n" +
                    "Пеногенератор STIHL для RE-230-462 (49255009600)\n" +
                    "Характеристики\n" +
                    "\n" +
                    "Совместимость : RE-230-462\n" +
                    "\n" +
                    "Объем, л : 1\n" +
                    "\n" +
                    "Полный список характеристик\n" +
                    "\n" +
                    "ХАРАКТЕРИСТИКИ ТОВАРА\n" +
                    "Совместимость\n" +
                    "RE-230-462\n" +
                    "Объем, л\n" +
                    "1\n" +
                    "ОПИСАНИЕ\n" +
                    "Пеногенератор STIHL (49255009600)\n" +
                    "\n" +
                    "- Высокая эффективность очистки благодаря долго не опадающей пене.\n" +
                    "- Вертикальная или горизонтальная регулировка распыляемой струи, регулировка угла распыления, кнопка дозатора для концентрированного моющего средства, литровая бутылка с большим заливным отверстием.\n" +
                    "- Для моделей RE 232 – RE 462.",6,R.drawable.pen)

        }
            binding.textKatalog.setOnClickListener {
                cViewModel.insertCategory("Все")
                cViewModel.insertCategory("Газонокосилки")
                cViewModel.insertCategory("Тракторы")
                cViewModel.insertCategory("Бензобуры")
                cViewModel.insertCategory("Мотокосы")
                cViewModel.insertCategory("Минимойки")
                cViewModel.insertCategory("Бензопилы")
            }




        categoryAdapter.onClickItem = {
            when(it.id){
                1 ->viewModel.list().observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                2 ->viewModel.getProductsByCategory(2).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                3 ->viewModel.getProductsByCategory(6).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                4 ->viewModel.getProductsByCategory(7).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                5 ->viewModel.getProductsByCategory(3).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                6 ->viewModel.getProductsByCategory(4).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                7 ->viewModel.getProductsByCategory(5).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }

            }
        }

        productAdapter.onClick = {
            val action =
                StoreFragmentDirections.actionStoreFragmentToAboutFragment(
                    it
                )
            findNavController().navigate(action)
        }

        binding.searchRv.setHasFixedSize(true)
        binding.searchRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.searchRv.adapter = searchAdapter

        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    viewModel.search(newText)

                return true
            }
        })


    }

}

